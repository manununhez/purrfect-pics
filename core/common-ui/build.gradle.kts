plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.manuelnunez.apps.core.ui"
  compileSdk = 34

  defaultConfig { minSdk = 21 }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  buildFeatures { compose = true }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.2" // androidxComposeCompiler
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)

  implementation(libs.androidx.compose.material3)

  // Compose
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)
}
