# PurrfectPics

PurrfectPics is your ultimate companion for discovering, customizing, and sharing adorable cat
images on Android. With a wide range of features, PurrfectPics brings joy to every cat lover's
heart.

## Features

- **Discover:** Explore a vast collection of random cat images sourced from the web.
- **Share:** Share delightful cat images with friends, family, and fellow cat enthusiasts with just
  a tap.
- **Save Favorites:** (Coming soon) Save your favorite cat images to easily revisit them later and
  create your personalized collection.
- **Splash Screen:** Engage users with a captivating splash screen animation while the app loads. (
  Note: There is a known issue with the splash screen not showing on Android 12. A temporary
  solution is to **open the app from the app tray**, as
  indicated [here](https://stackoverflow.com/questions/69812590/android-12-splash-screen-icon-not-displaying))

## Considerations

- FREE Cat APIs used in this version: [Cataas](https://cataas.com/)
  and [Pexels](https://www.pexels.com/). In particular, Pexels API
  is [rate-limited](https://www.pexels.com/api/documentation/#guidelines), in case of errors for
  this, a new API key will be necessary to be generated.
- Not persisted in DB or preferences because this API has dynamic and frequently updated data. On
  the other hand, Coil image lib does use disk and memory cache to smooth image loading.
- KtfmtFormat plugin applied for code formatting.

## Tools/Libraries

### Android Libraries

- **UI Components:** Essential libraries for building UI components and handling UI-related
  tasks. [AndroidX Core KTX](https://developer.android.com/jetpack/androidx/releases/core) | [Material Components for Android](https://github.com/material-components/material-components-android) | [Compose UI](https://developer.android.com/jetpack/androidx/releases/compose-ui)
- **Testing:** Frameworks and tools for writing and running tests to ensure code quality and
  reliability. [JUnit](https://junit.org/junit5/) | [MockK](https://mockk.io/) | [Turbine](https://github.com/cashapp/turbine)
- **Dependency Injection:** Tools for managing dependencies and implementing dependency injection in
  your project. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Coroutines:** Kotlin coroutine libraries for handling asynchronous programming tasks
  efficiently. [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- **Networking:** Libraries for making network requests and handling network
  communication. [Retrofit](https://square.github.io/retrofit/)
- **Image Loading:** Libraries for loading and displaying images efficiently in your
  app. [Coil](https://coil-kt.github.io/coil/)

### Compose Libraries

- **UI Components:** Libraries for building UI components using Jetpack
  Compose. [Material3](https://developer.android.com/jetpack/androidx/releases/compose-material3) | [Compose UI](https://developer.android.com/jetpack/androidx/releases/compose-ui)
- **Navigation:** Libraries for implementing navigation in Jetpack Compose
  apps. [Navigation Compose](https://developer.android.com/jetpack/androidx/releases/navigation) | [Hilt Navigation Compose](https://developer.android.com/training/dependency-injection/hilt-android#navigation-compose)
- **Material Design:** Libraries for implementing Material Design components and theming in Jetpack
  Compose. [Material Components for Android](https://github.com/material-components/material-components-android)

### Other

- **Build Tools:** Essential tools for building, testing, and packaging your Android
  app. [Android Gradle Plugin](https://developer.android.com/studio/releases/gradle-plugin) | [Kotlin Symbol Processing (KSP)](https://github.com/google/ksp)

## Screenshots

[Include screenshots or GIFs of the app in action]

## Getting Started

To get started with PurrfectPics, follow these steps:

1. Clone the repository to your local
   machine: `git clone git@github.com:manununhez/purrfect-pics.git`
2. Open the project in Android Studio.
3. Build and run the app on your device or emulator.

## Branching Strategy

- **Main Branch:** The primary branch of the project, reflecting the latest stable release.
- **Feature Branches:** Used for developing new features. Branch names should be descriptive of the
  feature being developed.
- **Epic Branches:** Used for grouping related features or significant enhancements. Branch names
  should reflect the overarching theme of the epic.
- **Release Branches:** Used for preparing releases. Branch names should follow the
  pattern `release/v<MAJOR>/<MAJOR>.<MINOR>.<PATCH>`.

## Contributing

We welcome contributions from the open-source community to help improve PurrfectPics. Whether you're
a developer, designer, or cat lover with ideas to share, we'd love to have you onboard! Here are
some ways you can contribute:

- Submit bug reports or feature requests via GitHub issues.
- Fork the repository, make changes, and submit pull requests for review.
- Help improve documentation, UI/UX design, or test coverage.

## License

PurrfectPics is licensed under the [MIT License](LICENSE).

## About

PurrfectPics is developed and maintained by [Manuel Nuñez](mailto:manuel.nunhez90@gmail.com). For
inquiries, please contact [manuel.nunhez90@gmail.com].
