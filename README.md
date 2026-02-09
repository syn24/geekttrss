Geekttrss
==========

Geekttrss is an Tiny Tiny Rss reader application with transparent offline mode for the Android platform.

For Copilot and automation guidance, see `AGENTS.md`.

You will need to install the web application [Tiny Tiny Rss](https://tt-rss.org/) and enable API access.
Then you will be able to access your Tiny Tiny Rss account from anywhere.

Geekttrss is an open source application and licensed under the GNU General Public License 3 and any later version.
This means that you can get Geekttrss's code and modify it to suit your needs, as long as you publish the changes
you make for everyone to benefit from as well.

Geekttrss is built and maintained by community volunteers.


Project Analysis & Architecture
===============================

The application is built using modern Android development practices, ensuring robustness and maintainability.

**Architecture**
- **Language**: Kotlin
- **UI Toolkit**: Jetpack Compose (migrated from/coexisting with View-based UI).
- **Architecture Pattern**: MVVM (Model-View-ViewModel) with Unidirectional Data Flow.
- **Dependency Injection**: Hilt.
- **Asynchronicity**: Kotlin Coroutines & Flow.
- **Data Persistence**: Room Database for offline storage.
- **Networking**: Retrofit for API communication.
- **Syncing**: WorkManager for background synchronization tasks.
- **Paging**: Paging 3 library for handling large lists of articles.

**Modules Structure**
- **`:app`**: The main Android application module containing UI, ViewModels, and integration logic.
- **`:webapi`**: Handles communication with the TT-RSS server API.
- **`:htmlparsers`**: Utilities for parsing and cleaning HTML content from feeds.
- **`:faviKonSnoop`**: A module dedicated to discovering and retrieving favicons for feeds.
- **`:manage_feeds`**: A specific feature module for feed management (Adding/Editing feeds).
- **`buildSrc`**: Contains custom Gradle plugins and convention logic to manage build configurations across modules.

**Key Features Deep Dive**
* **Synchronization & Offline Mode**: The app relies heavily on `WorkManager` to keep data in sync. It uses workers like `CollectNewArticlesWorker` and `SyncFeedsWorker` managed by `BackgroundJobManager`.
* **UI & Navigation**: The UI leverages Jetpack Compose with `LazyColumn` for lists and `HiltViewModel` for state management, offering a responsive and modern experience.

Build variants
==============

The project builds in 2 flavors :

   * The Google flavor is distributed on the [Google Play store](https://play.google.com/store/apps/details?id=com.geekorum.ttrss).
     It uses Crashlytics and other Google Play services to retrieve crash reports.

   * The free/libre flavor is distributed on [F-Droid](https://f-droid.org/packages/com.geekorum.ttrss.free/), and can also be built from the sources. It doesn't contains any Google Play services. The APK of the latest version of this flavor can also be downloaded from the [Releases Section](https://github.com/fbarthelery/geekttrss/releases/latest).


Build instructions
==================

Just use Gradle to build

    ./gradlew build

**Prerequisites**:
- JDK 17 (recommended for newer Android Gradle Plugins)
- Android Studio



