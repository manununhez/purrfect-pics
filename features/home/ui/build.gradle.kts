plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.features.home.ui"
  compileSdk = 34

  defaultConfig {
    minSdk = 21

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes { release { isMinifyEnabled = false } }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  composeOptions { kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get() }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  buildFeatures { compose = true }

  tasks.withType<Test> { useJUnitPlatform() }
}

dependencies {
  implementation(projects.core.common)
  implementation(projects.core.domain)
  implementation(projects.core.commonUi)
  implementation(projects.features.home.domain)

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

  // Instrumented tests: jUnit rules and runners
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.androidx.test.runner)
}
