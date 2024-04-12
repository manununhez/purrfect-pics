plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.hilt.gradle)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.manuelnunez.apps.purrfectpics"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  buildFeatures { compose = true }

  composeOptions { kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get() }

  tasks.withType<Test> { useJUnitPlatform() }

  packaging { resources { excludes.add("META-INF/{LICENSE-notice.md,LICENSE.md}") } }
}

dependencies {
  implementation(projects.core.commonUi)
  implementation(projects.core.data)
  implementation(projects.features.home.ui)

  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.core.ktx)

  implementation(libs.androidx.activity.compose)

  implementation(libs.androidx.compose.material3)

  implementation(libs.coil.kt)

  // Compose
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)

  // Hilt Dependency Injection
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}
