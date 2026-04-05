# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Geekttrss is an Android Tiny Tiny RSS reader with transparent offline mode. It uses Kotlin throughout, with Jetpack Compose as the primary UI framework alongside some legacy View-based UI.

## Build Commands

```bash
./gradlew build                                    # Build entire project
./gradlew :app:assembleDebug                       # Build debug APK
./gradlew :app:testFreeDebugUnitTest               # Run unit tests (free flavor)
./gradlew :app:testGoogleDebugUnitTest             # Run unit tests (google flavor)
./gradlew :app:connectedFreeDebugAndroidTest       # Run instrumented tests (free flavor)

# Run a single test class or method
./gradlew :app:testFreeDebugUnitTest --tests "com.geekorum.ttrss.SomeTest"
./gradlew :app:testFreeDebugUnitTest --tests "com.geekorum.ttrss.SomeTest.someMethod"
```

**Prerequisites:** JDK 17, Android Studio

## Module Structure

- **`:app`** — Main application module (UI, ViewModels, navigation, database integration)
- **`:webapi`** — Tiny Tiny RSS API client (Retrofit + Kotlinx Serialization + OkHttp3)
- **`:htmlparsers`** — HTML content parsing and cleaning (KSoup)
- **`:faviKonSnoop`** — Favicon discovery and retrieval
- **`buildSrc`** — Convention plugins and shared build configuration

Note: `:manage_feeds` module exists but is currently disabled in `settings.gradle.kts`.

## Architecture

**Pattern:** MVVM with Unidirectional Data Flow

**Key layers:**
- ViewModels expose `StateFlow<PagingData<>>` consumed by Compose UI
- Repositories handle data access (Room database + Network)
- WorkManager workers (`CollectNewArticlesWorker`, `SyncFeedsWorker`) handle background sync, managed by `BackgroundJobManager`

**Key technologies:**
- **DI:** Hilt (with `HiltRunner` for instrumented tests)
- **Async:** Kotlin Coroutines & Flow throughout
- **DB:** Room (pinned to 2.6.1 — do not upgrade to 2.7.x, critical bug)
- **Networking:** Retrofit 3.0.0
- **Paging:** Paging 3

## Build Flavors

- `free` — F-Droid distribution, no Google Play services
- `google` — Google Play Store distribution, includes Crashlytics

## Dependency Management

All dependency versions are centralized in `gradle/libs.versions.toml`. Build conventions are in `buildSrc/src/main/kotlin/conventions/`.

## Key File Locations

- **Database entities/DAOs:** `app/src/main/java/com/geekorum/ttrss/data/`
- **Sync workers:** `app/src/main/java/com/geekorum/ttrss/sync/`
- **Article list UI:** `app/src/main/java/com/geekorum/ttrss/articles_list/`
- **ProGuard rules:** `app/proguard-rules.pro`

## Feed Display Behavior

- **Fresh Articles:** Only unread articles from the last 36 hours
- **All Articles:** All articles regardless of read status
- **Starred / Published:** All articles regardless of read status
- **Normal feeds:** Only unread articles (no UI setting to change this; internal `PREF_VIEW_MODE` defaults to `"adaptive"`)

Feed icons are synced only when `EXTRA_UPDATE_FEED_ICONS = true` is set; `BackgroundJobManager.refresh()` sets this on every manual pull-to-refresh.

## Known Issues & Fixes

**Article display (resolved):** All feed types use direct repository access in `ArticlesViewModel` — no `ArticlesAccess` indirection. This avoids caching issues where `cachedIn(viewModelScope)` would retain stale data after feed switches.

**Memory leaks (resolved):**
- `MainActivity`: uses `Lifecycle.State.STARTED` (not `CREATED`) for `repeatOnLifecycle`, with `return@collect` after `finish()`
- `ArticleCardsList`: `LaunchedEffect(articles, isRefreshing)` instead of `LaunchedEffect(Unit)` so the `snapshotFlow` is cancelled on dependency changes

**Article cleanup:** Purge worker deletes articles older than 1 day (not 90) to prevent OOM; purge runs immediately on app start.

## Gradle Tips

- Gradle JVM heap is set to 4 GB
- If you encounter memory/daemon issues: `./gradlew --stop`

## Lint

Lint is configured with `abortOnError = false` and `checkReleaseBuilds = false`. `MissingTranslation` warnings are suppressed.