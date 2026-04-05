# GeekTTRSS — Architecture & Main Flow

## Overview

GeekTTRSS is a Kotlin-first Android app following **MVVM with Unidirectional Data Flow**. The UI is built in Jetpack Compose, state is managed with `StateFlow`/`Flow`, data is persisted in a Room database, and background sync runs through WorkManager.

The app has two primary screens: the **articles list** and the **article detail view**. This document traces the complete path from app launch through both screens.

---

## Startup & Account Check

**`MainActivity`** is the launcher activity. Its only job is to check whether a TT-RSS account has been configured. If one exists it immediately starts **`ArticleListActivity`** and finishes itself. If no account is found it redirects to account setup.

---

## Articles List Screen

### Activity: `ArticleListActivity`

`ArticleListActivity` is the root host for the articles list experience. It:

- Verifies the session is valid (inherits from `SessionActivity`)
- Creates a set of activity-scoped ViewModels (feeds, tags, in-app updates, review prompts)
- Calls `setContent {}` to render the full Compose UI tree

### Scaffold: `ArticlesListScaffold`

The top-level composable. It wires together the structural shell:

- **Navigation drawer** — lists all feeds and categories; tapping one navigates into its article list
- **Top app bar** — feed title, search, filter (unread-only / sort order)
- **Floating action button** — triggers a manual refresh
- **In-app update banner** — shown when an update is pending

### Navigation: `ArticlesListNavHost` / `Navigation.kt`

A Compose `NavHost` lives inside the scaffold content area. Routes:

| Route | Screen | ViewModel |
|---|---|---|
| `ArticlesList(feedId)` | `ArticlesListScreen` | `ArticlesListViewModel` |
| `ArticlesListByTag(tag)` | `ArticlesListByTagScreen` | `ArticlesListByTagViewModel` |
| `Search(query)` | `ArticlesSearchScreen` | — |

Deep links follow the pattern `app://feeds/{feed_id}`.

### Screen: `ArticlesListScreen`

Delegates to `BaseArticlesListScreen`, which renders `ArticleCardList` — a `LazyColumn` backed by Paging 3's `collectAsLazyPagingItems()`. It handles:

- Pull-to-refresh via `PullToRefreshBox`
- Scroll-to-top after a refresh completes
- Displaying a snackbar with an "Undo" action when articles are marked read

### Item: `ArticleCard`

Each article in the list is an `ArticleCard`. It supports:

- Swipe-to-dismiss to mark as read/unread
- Star toggle button
- Share button
- A compact variant (`CompactArticleListItem`) controlled by a user preference

Tapping the card calls `ActivityViewModel.displayArticle(position, article)`.

### ViewModels

**`ActivityViewModel`** (activity-scoped) is the coordination hub:

- Holds the `articleSelectedEvent` — a `LiveData<Event<…>>` that fires when a card is tapped, observed by the activity to trigger navigation
- Exposes user preferences as `Flow`s: sort order, unread-only filter, compact layout
- Manages the undo snackbar state for "mark as read" actions
- Owns `browserIcon` — the icon of the default browser app shown on the open-in-browser button

**`ArticlesListViewModel`** (per-destination, assisted-injected with `feedId`):

- Exposes `articles: Flow<PagingData<ArticleWithFeed>>` — the paginated article stream, cached in `viewModelScope`
- Builds the correct DB query based on feed type:
  - Virtual feeds: Fresh Articles (`-3`), All Articles (`-4`), Starred (`-1`), Published (`-2`)
  - Regular feeds: unread articles for that specific feed ID
- Calls `BackgroundJobManager` to trigger syncs
- Wraps read/star mutations in `SetUnreadAction` / `SetStarredAction` objects that support undo via `UndoManager`

**`BaseArticlesViewModel`** is the shared base class. It manages:

- `sortByMostRecentFirst` and `needUnread` — `MutableStateFlow`s that feed into the DB query selection
- The `UndoManager` and pending-unread counter
- The four `ArticlesAccess` inner classes (`UnreadMostRecentAccess`, `UnreadOldestAccess`, `MostRecentAccess`, `OldestFirstAccess`) that each return the right `PagingSource` for the current sort/filter combination

### Repositories & Data Access

**`ArticlesRepository`** is a thin facade over `ArticleDao`. All article list queries return `PagingSource<Int, ArticleWithFeed>` so Paging 3 can load pages on demand. Mutation methods (`setArticleUnread`, `setArticleStarred`) write to the local DB and queue an API call; if the API call fails the change is saved as a pending `Transaction` to be sent later.

**`FeedsRepository`** combines `FeedsDao` (real feeds/categories from the DB) with computed counts for the three virtual feeds (Fresh, All Articles, Starred) to produce the navigation drawer list.

**`ArticleDao`** / **`FeedsDao`** — Room DAOs. Queries are plain SQL; Room emits new results automatically when the underlying table changes.

---

## Opening an Article

When the user taps an `ArticleCard`:

1. `ActivityViewModel.displayArticle(position, article)` posts to `articleSelectedEvent`
2. `ArticleListActivity` observes this event and calls `navController.navigateToArticle(articleId)`
3. `navigateToArticle` creates an `Intent` with URI `app://article/{articleId}` and starts `ArticleDetailActivity`

This is an Activity-to-Activity transition (not an in-graph Compose navigation) because the detail screen is a separate, independently launchable component.

---

## Article Detail Screen

### Activity: `ArticleDetailActivity`

Parses `articleId` from the incoming URI, builds an `ArticleDetailsViewModel` via assisted injection, and calls `setContent {}`. It also registers an `ArticleDetailsEntryPoint` used by the embedded WebView to access Hilt-provided dependencies.

### Screen: `ArticleDetailsScreen`

Routes to one of two layouts:

- **`ArticleDetailsScreenHero`** — used on most phones; full-height hero image at the top, scrollable content below
- **Compact variant** — used on smaller screens

The hero layout contains:

- **`ArticleTopActionsBar`** — collapsing top bar with the feed name, read toggle, star toggle, share, and back navigation
- **WebView** — renders the article's HTML body; uses a custom `WebViewClient` to intercept link clicks and open them in-app or in the browser
- **`OpenInBrowserExtendedFab`** — appears when the user scrolls to the end of the article, offering to open the full article in the browser
- **"Read more" section** — up to three related articles from the same tag(s), shown at the bottom

The screen automatically marks the article as read when the user scrolls past the end (`ArticleDetailsScreenState` tracks this via `nestedScrollConnection`).

### ViewModel: `ArticleDetailsViewModel`

Assisted-injected with `articleId`. Key responsibilities:

- `article: StateFlow<Article?>` — subscribes to `ArticlesRepository.getArticleById(articleId)` so the UI always reflects the current DB state (e.g., if starred status changes)
- `additionalArticles: StateFlow<List<ArticleWithTag>>` — finds up to 3 other unread articles sharing a tag with this article, used for the "read more" section
- `toggleArticleRead()` / `onStarChanged()` — delegate to `SetUnreadAction` / `SetStarredAction` (same undo-capable actions used in the list)
- `openArticleInBrowser()` / `shareArticle()` — fire Android intents

---

## Key Data Classes (`Types.kt`)

| Class | Purpose |
|---|---|
| `Article` | Room entity; holds all article fields: `id`, `title`, `content`, `link`, `author`, `isUnread`, `isStarred`, `isPublished`, `lastTimeUpdate`, `feedId`, `flavorImageUri`, `contentExcerpt`, `tags` |
| `ArticleWithFeed` | Room relation joining `Article` + `Feed`; used throughout the list to show the feed name and favicon alongside each article |
| `Feed` | Room entity for a subscription. Contains `FEED_ID_*` constants for virtual feeds: Fresh (`-3`), All Articles (`-4`), Starred (`-1`), Published (`-2`) |
| `FeedWithFavIcon` | Feed + its favicon bitmap; used in the navigation drawer |
| `Category` | Feed category/folder |
| `ArticleWithAttachments` | Article + media attachments; used during sync |

---

## Sync Architecture (Background)

Sync is triggered either on a schedule or by a manual refresh call in the ViewModel. The flow is:

```
BackgroundJobManager.refresh()
    → ContentResolver.requestSync()
        → ArticleSyncAdapter.onPerformSync()
            → ArticleSynchronizer.sync()
                1. SyncFeedsWorker          — updates feed metadata & unread counts
                2. CollectNewArticlesWorker — fetches new article content per feed
                3. UpdateArticleStatusWorker — syncs read/star status changes
                4. PurgeArticlesWorker      — deletes read articles older than 1 day
```

Each step runs as a WorkManager `OneTimeWorkRequest`. Steps are coordinated by `ArticleSynchronizer` using `WorkManager.beginWith(...).then(...)` chains and tag-based completion tracking.

`CollectNewArticlesWorker` uses `sinceId = getLatestArticleId()` so it only fetches articles the device doesn't already have. For non-Fresh feeds it applies a 24-hour cutoff to limit DB size.

---

## Article State Changes

Marking an article read, unread, or starred follows this path:

```
User action (swipe / button tap)
    → BaseArticlesViewModel.setArticleUnread() / setArticleStarred()
        → SetUnreadAction / SetStarredAction created & executed
            → ArticleDao.updateArticleUnread()   (immediate local update)
            → ApiService.updateArticleField()    (network call)
                → if network fails: saved as Transaction in TransactionsDao
                    → sent on next sync via SendTransactionsWorker
```

The action object is also stored in `UndoManager`. If the user taps "Undo" in the snackbar, the action is reversed before it can be committed.
