# Clean MVVM app with Coroutine, Flow, RoomDB, Koin and MockWebServer.

This sample demo app shows the current trending Github repositories fetched from a public API. It follows below features:

* Kotlin with MVVM and Room  
* Follows reactive paradigm (Rx or Livedata or Flow)
* Koin library with proper abstraction for Dependency Injection
* Caching - The app should have 100% offline support. Once the data is fetched successfully from remote, it stored locally and served from cache until the cache expires.
* Uses Espresso and MockWebserver for UI testing
* Pull-to-refresh option to the user to force fetch data from remote.

