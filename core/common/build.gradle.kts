plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.core.common"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  defaultConfig { minSdk = 21 }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  packaging { resources { excludes.add("META-INF/{LICENSE-notice.md,LICENSE.md}") } }
}

dependencies {
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.test)
  implementation(libs.junit.api)
  implementation(libs.mockk)

  // HILT
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}