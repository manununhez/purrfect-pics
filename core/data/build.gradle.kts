plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.core.data"
  compileSdk = 34

  defaultConfig { minSdk = 21 }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}

dependencies {
  implementation(projects.core.common)
  implementation(projects.core.services)
  implementation(projects.features.home.domain)

  implementation(libs.retrofit.core)
  implementation(libs.retrofit.gsonConverter)

  // HILT
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  testImplementation(libs.junit)
  testImplementation(libs.mockk)
}
