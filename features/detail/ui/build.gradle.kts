plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.features.detail.ui"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  defaultConfig {
    minSdk = 21
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  buildFeatures { compose = true }

  composeOptions { kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get() }

  tasks.withType<Test> { useJUnitPlatform() }

  packaging { resources { excludes.add("META-INF/{LICENSE-notice.md,LICENSE.md}") } }

  buildTypes { release { isMinifyEnabled = false } }
}

dependencies {
  implementation(projects.core.common)
  implementation(projects.core.domain)
  implementation(projects.core.commonUi)

  // Arch Components
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.hilt.navigation.compose)

  // Hilt
  implementation(libs.hilt.android)
  debugImplementation(libs.androidx.ui.tooling)
  ksp(libs.hilt.compiler)

  // Coil
  implementation(libs.coil.kt)
  implementation(libs.coil.kt.compose)
  implementation(libs.coil.kt.gif)

  // Compose
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)

  // Local tests: jUnit, coroutines, Android runner
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.mockk)
  testImplementation(libs.turbine)

  api(libs.androidx.compose.ui.test.junit4)
  debugApi(libs.androidx.compose.ui.test.manifest)
}
