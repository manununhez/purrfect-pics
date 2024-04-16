import java.io.FileInputStream
import java.util.Properties

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.core.services"
  compileSdk = 34

  defaultConfig { minSdk = 21 }

  buildTypes {
    val keyStoreFile = rootProject.file("keystore.properties")

    val keystoreProperties =
        if (keyStoreFile.exists()) {
          Properties().apply { load(FileInputStream(keyStoreFile)) }
        } else {
          null
        }

    val pexelsKey = keystoreProperties?.getProperty("PEXELS_API_KEY") ?: "\"\""

    debug { buildConfigField("String", "PEXELS_API_KEY", pexelsKey) }

    release {
      buildConfigField("String", "PEXELS_API_KEY", pexelsKey)
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  buildFeatures { buildConfig = true }

  packaging { resources { excludes.add("META-INF/{LICENSE-notice.md,LICENSE.md}") } }
}

dependencies {
  implementation(projects.core.common)

  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  implementation(libs.retrofit.core)
  implementation(libs.retrofit.gsonConverter)
  implementation(libs.okhttp.logging)
}
