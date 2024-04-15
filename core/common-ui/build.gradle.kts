plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.manuelnunez.apps.core.ui"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  defaultConfig { minSdk = 21 }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  buildFeatures { compose = true }

  composeOptions { kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get() }
}

dependencies {
  implementation(libs.androidx.core.ktx)

  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui.tooling.preview)

  implementation(libs.coil.kt.compose)

  implementation(libs.androidx.navigation.compose)

  // Compose
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  debugImplementation(libs.androidx.ui.tooling)
}