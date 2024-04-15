plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
  id("kotlin-parcelize")
}

android {
  namespace = "com.manuelnunez.apps.core.domain"
  compileSdk = 34

  defaultConfig { minSdk = 21 }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  packaging { resources { excludes.add("META-INF/{LICENSE-notice.md,LICENSE.md}") } }
}

dependencies {
  implementation(projects.core.common)

  // HILT
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}
