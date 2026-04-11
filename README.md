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

To build: use build signed apk free release version!


Build instructions
==================

Just use Gradle to build

    ./gradlew build

**Prerequisites**:
- JDK 17 (recommended for newer Android Gradle Plugins)
- Android Studio

Test instructions
=============================
# All tests: 

gradlew :app:testDebugUnitTest :app:connectedDebugAndroidTest
gradlew testDebugUnitTest connectedDebugAndroidTest

- With :app: — runs only that task in the :app module. Faster, targeted.
- Without :app: — Gradle runs the task in every module that has it. In this project that would include :webapi, :htmlparsers, :faviKonSnoop if they define testDebugUnitTest. Slower but more thorough.

For day-to-day work you almost always want the :app: prefix since that's where all the code you're touching lives. The bare form is more useful in CI where you want everything verified.

# Einzelnen Test ausführen
gradlew :app:testDebugUnitTest --tests "com.geekorum.ttrss.network.RealServerIntegrationTest.testLogin"

# Performance-Benchmark ausführen
gradlew :app:testFreeUnitTest --tests "com.geekorum.ttrss.network.RealServerIntegrationTest.testPerformanceBenchmark"

# Alle Integrationstests ausführen
gradlew :app:testDebugUnitTest --tests "com.geekorum.ttrss.network.RealServerIntegrationTest"

1. Run every instrumented test in one shot

gradlew :app:connectedDebugAndroidTest

This runs all of androidTest/ — including the 35 new tests plus any pre-existing ones (ArticleFullTextSearchTest, CollectNewArticlesWorkerTest, etc.). Expect 5-15 minutes on first run because Hilt generates a lot of test components.

2. Run a single test class

AGP supports the --tests filter on connectedDebugAndroidTest (AGP 9.1.0):

gradlew :app:connectedDebugAndroidTest --tests "com.geekorum.ttrss.data.ArticleDaoTest"
gradlew :app:connectedDebugAndroidTest --tests "com.geekorum.ttrss.sync.workers.SyncFeedsWorkerTest"
gradlew :app:connectedDebugAndroidTest --tests "com.geekorum.ttrss.sync.workers.SyncFeedsIconWorkerTest"
gradlew :app:connectedDebugAndroidTest --tests "com.geekorum.ttrss.sync.workers.PeriodicRefreshWorkerTest"
gradlew :app:connectedDebugAndroidTest --tests "com.geekorum.ttrss.background_job.BackgroundJobManagerTest"

3. Run a single test method

gradlew :app:connectedDebugAndroidTest --tests "com.geekorum.ttrss.background_job.BackgroundJobManagerTest.refreshFeedEnqueuesUniqueWorkWithExpectedName"

4. Run the whole "new Phase 3+4 worker batch"

gradlew :app:connectedDebugAndroidTest \
--tests "com.geekorum.ttrss.sync.workers.*" \
--tests "com.geekorum.ttrss.background_job.*"

The filter accepts glob patterns so multiple --tests flags stack.

5. Where to find results

- HTML report: app/build/reports/androidTests/connected/debug/index.html — open in a browser, click through failures to see stack traces
- XML (CI-friendly): app/build/outputs/androidTest-results/connected/debug/
- Logcat from the run: app/build/outputs/androidTest-results/connected/debug/logcat-*.txt

6. Useful flags

# Stream test names as they run (nice for long runs)
./gradlew :app:connectedDebugAndroidTest -i

# Force reinstall of the test APK (rarely needed, but useful if you see "process crashed")
./gradlew :app:connectedDebugAndroidTest --rerun-tasks

# If the daemon misbehaves after a WHPX recovery
./gradlew --stop && ./gradlew :app:connectedDebugAndroidTest

7. The unit-test counterpart (no emulator)

For comparison — the ~49 Robolectric/JVM tests from Phases 1.1, 2.1, 2.2, 5 run without any device:

./gradlew :app:testDebugUnitTest
./gradlew :app:testDebugUnitTest --tests "com.geekorum.ttrss.article_details.ReadMoreSectionTest"

Those hit app/build/reports/tests/testDebugUnitTest/index.html instead.

Gotcha to watch for

The BackgroundJobManagerTest tests that call refreshFeed(..., feedId = 42) use ExistingWorkPolicy.KEEP under the hood, so if you run the test twice back-to-back against the same emulator without a clean WorkManager DB, refreshFeed-7 and refreshFeed-42 entries could survive across runs and affect assertions. The WorkManager test harness resets state per JVM process, so a clean ./gradlew invocation is always fine
— but if you use Android Studio's "rerun failed" repeatedly, wipe the emulator's app data once: adb shell pm clear com.geekorum.geekttrss.


