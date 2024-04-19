# PurrfectPics ![ic_launcher_round](https://github.com/manununhez/purrfect-pics/assets/5048531/1fab47b6-03fb-4901-b6c9-0fe60cbaecd1)

PurrfectPics is your ultimate companion for discovering, customizing, and sharing adorable cat
images on Android. With a wide range of features, PurrfectPics brings joy to every cat lover's
heart.

## Features

- **Discover:** Explore a vast collection of random cat images sourced from the web.
- **Share:** Share delightful cat images with friends, family, and fellow cat enthusiasts with just
  a tap.
- **Save Favorites:** Save your favorite cat images to easily revisit them later and
  create your personalized collection.

## Tools/Libraries

- **UI Components:** [AndroidX Core KTX](https://developer.android.com/jetpack/androidx/releases/core) | [Material Components for Android](https://github.com/material-components/material-components-android) | [Compose UI](https://developer.android.com/jetpack/androidx/releases/compose-ui)
- **Compose UI:**   [Compose UI](https://developer.android.com/jetpack/androidx/releases/compose-ui)
- **Testing:** [JUnit](https://junit.org/junit5/) | [MockK](https://mockk.io/) | [Turbine](https://github.com/cashapp/turbine)
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Coroutines:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- **DataStore Proto:** [DataStore Proto](https://developer.android.com/topic/libraries/architecture/datastore#prefs-vs-proto)
- **Navigation:** [Navigation Compose](https://developer.android.com/jetpack/androidx/releases/navigation) | [Hilt Navigation Compose](https://developer.android.com/training/dependency-injection/hilt-android#navigation-compose)
- **Material Design:** [Material3](https://developer.android.com/jetpack/androidx/releases/compose-material3) [Material Components for Android](https://github.com/material-components/material-components-android)
- **Networking:** [Retrofit](https://square.github.io/retrofit/)
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/)

## Screenshots

| ![Screenshot 2024-04-16 at 10 26 30 AM](https://github.com/manununhez/purrfect-pics/assets/5048531/c08f645e-a362-4d2e-bfff-c99d8966e2b8) | ![Screenshot 2024-04-19 at 12 06 41 AM](https://github.com/manununhez/purrfect-pics/assets/5048531/f7c9873c-8ab8-4215-b4f3-ed801dd8f6c9) | ![Screenshot 2024-04-19 at 12 17 14 AM](https://github.com/manununhez/purrfect-pics/assets/5048531/534f2f34-5f9d-4945-83bf-0076f667bc19) | ![Screenshot 2024-04-19 at 12 10 40 AM](https://github.com/manununhez/purrfect-pics/assets/5048531/69042702-71aa-4eae-b508-871d9c630ff8) | ![Screenshot 2024-04-19 at 12 16 02 AM](https://github.com/manununhez/purrfect-pics/assets/5048531/e55d1bae-6aa4-4401-a6b6-8754b0756efd) | ![Screenshot 2024-04-19 at 2 15 49 PM](https://github.com/manununhez/purrfect-pics/assets/5048531/b4185b5a-85b7-47e0-91df-ab0e0d822c42) |
|:--:|:--:|:--:|:--:|:--:|:--:|
| Splashscreen | Home - Featured and popular items | Popular paginated items | Your favorites cats | Detail image (share and favorite button) | Detail image with zoomable elements|

## Considerations

- FREE Cat APIs used in this version: [Cataas](https://cataas.com/)
  and [Pexels](https://www.pexels.com/). In particular, Pexels API
  is [rate-limited](https://www.pexels.com/api/documentation/#guidelines), in case of errors for
  this, a new API key will be necessary to be generated.
- Localdata persistence of favorite cats only. Responses from API are not cached because these are dynamic and frequently updated data. On
  the other hand, Coil image lib does use disk and memory cache to smooth image loading.
- KtfmtFormat plugin applied for code formatting.
- There is a known issue with the splash screen not showing on Android 12. A temporary
  solution is to **open the app from the app tray**, as
  indicated [here](https://stackoverflow.com/questions/69812590/android-12-splash-screen-icon-not-displaying))

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
