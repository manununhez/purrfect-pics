plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.protobuf)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.manuelnunez.apps.core.datastore.proto"
  compileSdk = 34

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  defaultConfig { minSdk = 21 }

  kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

  tasks.withType<Test> { useJUnitPlatform() }
}

// Setup protobuf configuration, generating lite Java and Kotlin classes
protobuf {
  protoc { artifact = libs.protobuf.protoc.get().toString() }
  generateProtoTasks {
    all().forEach { task ->
      task.builtins {
        register("java") { option("lite") }
        register("kotlin") { option("lite") }
      }
    }
  }
}

dependencies {
  implementation(projects.core.common)

  implementation(libs.protobuf.kotlin.lite)
  implementation(libs.androidx.dataStore.core)

  // HILT
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  testImplementation(libs.junit)
}
