import java.io.FileInputStream
import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.hilt.gradle)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  defaultConfig {
    applicationId = "com.manuelnunez.apps.purrfectpics"
    minSdk = 21
    targetSdk = 34
    versionCode = 4
    versionName = "1.1.1"
    testInstrumentationRunner = "com.manuelnunez.apps.navigation.CustomTestRunner"
  }

  signingConfigs {
    create("release") {
      val keyStoreFile = rootProject.file("keystore.properties")

      if (keyStoreFile.exists()) {
        val keystoreProperties = Properties().apply { load(FileInputStream(keyStoreFile)) }

        with(keystoreProperties) {
          storeFile = file(getProperty("KEYSTORE_FILE"))
          keyAlias = getProperty("KEY_ALIAS")
          keyPassword = getProperty("KEY_PASSWORD")
          storePassword = getProperty("KEY_PASSWORD")
        }
      }
    }
  }

  buildFeatures { compose = true }

  composeOptions { kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get() }

  tasks.withType<Test> { useJUnitPlatform() }

  packaging { resources { excludes.add("META-INF/{LICENSE-notice.md,LICENSE.md}") } }

  buildTypes {
    getByName("release") {
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      isMinifyEnabled = true // Enables code shrinking for the release build type.
      isShrinkResources = true
      signingConfig = signingConfigs.getByName("release")
      isDebuggable = false
    }

    getByName("debug") {
      applicationIdSuffix = ".debug"
      isMinifyEnabled = false
      isDebuggable = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation(projects.core.data) // Added only for testing using the TestDataModule
  implementation(projects.core.ui)
  implementation(projects.core.domain)

  implementation(projects.features.home.ui)
  implementation(projects.features.detail.ui)
  implementation(projects.features.seemore.ui)
  implementation(projects.features.favorites.ui)

  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.core.ktx)

  implementation(libs.androidx.activity.compose)

  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.navigation.compose)

  implementation(libs.coil.kt)
  implementation(libs.androidx.test.runner)

  // Compose
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)

  // Hilt Dependency Injection
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  androidTestImplementation(libs.androidx.navigation.testing)
  androidTestImplementation(libs.hilt.android.testing)
}
