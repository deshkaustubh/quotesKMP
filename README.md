# QuotesKMP — Kotlin Multiplatform Compose app
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


A Kotlin Multiplatform app built with Compose Multiplatform that runs on:
- Android
- Desktop (JVM, Compose for Desktop)
- Web (JavaScript, Compose for Web)
- WebAssembly (Wasm JS, experimental)

It showcases quotes browsing with Home, Explore, and Favorites, a bottom navigation bar, theming (light/dark), and a cross‑platform Share API.


## Project layout

- `composeApp/` — Main multiplatform module
  - `src/commonMain/` — Shared UI, navigation, view models, models
  - `src/androidMain/` — Android actuals and Activity
  - `src/jvmMain/` — Desktop actuals and entry point
  - `src/jsMain/` — Web JS actuals
  - `src/wasmJsMain/` — WebAssembly JS actuals (experimental)
  - `src/webMain/` — Web application bootstrap (Compose for Web)
- Gradle setup: Kotlin Multiplatform + Compose Multiplatform, with tasks for each target

Key source files to explore:
- `App.kt` — Root composable and theme wiring
- `AppNavGraph.kt` — Navigation graph between Home, Explore, Saved
- `view/components/*.kt` — Reusable composables (BottomNavigationBar, QuotesCard, SectionHeader, etc.)
- `view/screens/*.kt` — Screens (HomeScreen, ExploreScreen, SavedScreen)
- `share/ShareManager.kt` — expect/actual API for text sharing


## Prerequisites

- JDK 11 (Gradle config targets JVM 11)
- Android Studio (Hedgehog+ recommended) or IntelliJ IDEA with Kotlin and Compose plugins
- Android SDKs: minSdk, targetSdk defined in Gradle
- Node.js (optional, for local JS/Wasm dev tooling if needed)

On Windows, use PowerShell (default) with the commands below.


## Getting started

Clone and open the project, then run the target you want.

### 1) Android

- Build and install debug APK on a connected device or emulator:

```powershell
./gradlew :composeApp:installDebug ; adb shell am start -n tech.kaustubhdeshpande.quoteskmp/tech.kaustubhdeshpande.quoteskmp.MainActivity
```

- Alternatively, from Android Studio: Run the `composeApp` configuration (MainActivity).

### 2) Desktop (JVM)

Run the Desktop app with Compose for Desktop:

```powershell
./gradlew :composeApp:run
```

You can also build native installers (MSI/Deb/DMG) defined under `compose.desktop`:

```powershell
./gradlew :composeApp:packageDistributionForCurrentOS
```

### 3) Web (JavaScript)

Start the browser target (Compose for Web JS):

```powershell
./gradlew :composeApp:jsBrowserRun
```

This will start a dev server and open your default browser. If it doesn’t open automatically, check the terminal URL.

### 4) WebAssembly (Wasm JS, experimental)

Run the experimental Wasm target:

```powershell
./gradlew :composeApp:wasmJsBrowserRun
```

Note: Wasm Compose is evolving; some features may be limited.


## Key features to try

- Bottom navigation: Home, Explore, Saved
- Section headers in Home with navigation intents
- Favorites: Tap heart on a quote card to add/remove
- Share: Use the share action to share the quote text (platform‑specific behavior)
- Theme toggle: Switch between light and dark themes


## Usage notes and tips

- Navigation
  - Bottom bar uses Compose Navigation; if you navigate via section headers in Home, ensure the route uses `popUpTo` appropriately to keep the expected back stack behavior. Consider a TopAppBar with tabs if you prefer single‑source navigation state.

- Theme toggle
  - The app theme is defined in `ui/theme/Theme.kt`. If your toggle isn’t changing the theme, verify that the state controlling `darkTheme` flows from a `rememberSaveable` or ViewModel and is used when calling `QuotesKMPTheme(darkTheme = ...)` at the app root.

- Share API
  - Common `expect class ShareManager` with `fun shareText(text: String, subject: String? = null)`
  - Android actual uses `Intent.ACTION_SEND`
  - Desktop actual uses clipboard + mailto fallback
  - JS/Wasm actuals are currently no‑ops (you can implement `navigator.share` for supported browsers, or fallback to clipboard)


## Troubleshooting

- Build errors: Ensure JDK 11 and Android SDK versions match `composeApp/build.gradle.kts`.
- Compose previews: Use Android Studio with Compose tooling enabled; previews may require Android target.
- JS/Wasm tasks not found: Make sure you’re running the commands on the `composeApp` module (`:composeApp:jsBrowserRun`, `:composeApp:wasmJsBrowserRun`).
- Red symbol `shareText`: Confirm you import `tech.kaustubhdeshpande.quoteskmp.share.ShareManager` and call a valid actual for the current platform. On Android, construct with a `Context` (`ShareManager(context)`), on other targets use the no‑arg actual where available.
- Dark mode toggle not working: Verify the icon’s `onClick` changes the theme state at the root, not only inside a child composable. If the theme composable doesn’t recompose, lift the state up to `App.kt`.


## Development commands

- Lint/format:

```powershell
./gradlew ktlintFormat ; ./gradlew ktlintCheck
```

- Run unit tests:

```powershell
./gradlew :composeApp:check
```

- Clean build:

```powershell
./gradlew clean ; ./gradlew build
```


## Git ignore recommendations for KMP / Compose projects

Include (or keep) entries like the following in `.gitignore` to avoid committing local/derived files:

```
# Gradle
.gradle/
build/
**/build/

# IDEs
.idea/
*.iml

# Android
local.properties
*.apk
*.keystore

# Kotlin JS/Wasm tooling caches
kotlin-js-store/
node_modules/

# OS
.DS_Store
Thumbs.db
```

If you package Desktop installers, don’t commit generated installer artifacts; keep only sources.



## Acknowledgements

- JetBrains Compose Multiplatform
- Kotlin Multiplatform Mobile
- AndroidX Navigation Compose
- Material3 Compose

