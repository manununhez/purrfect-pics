plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.features.home.domain"
  compileSdk = 34

  defaultConfig { minSdk = 21 }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }
}

dependencies {
  implementation(projects.core.common)
  implementation(projects.core.domain)

  implementation(libs.kotlinx.coroutines.android)

  // HILT
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}
