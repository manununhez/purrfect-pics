plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.core.data"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  defaultConfig { minSdk = 21 }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  tasks.withType<Test> { useJUnitPlatform() }

  packaging { resources { excludes.add("META-INF/{LICENSE-notice.md,LICENSE.md}") } }
}

dependencies {
  implementation(projects.core.common)
  implementation(projects.core.services)
  implementation(projects.core.domain)
  implementation(projects.features.home.domain)
  implementation(projects.features.seemore.domain)

  implementation(libs.retrofit.core)
  implementation(libs.retrofit.gsonConverter)

  // HILT
  implementation(libs.hilt.android)
  implementation(libs.androidx.paging.common.ktx)
  ksp(libs.hilt.compiler)

  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.junit)
  testImplementation(libs.turbine)
  testImplementation(libs.mockk)
}
