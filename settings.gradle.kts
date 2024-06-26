pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Purrfect_Pics"

include(":app")

include(":core:common")

include(":core:data")

include(":core:datastore-proto")

include(":core:domain")

include(":core:services")

include(":core:ui")

include(":features:home:domain")

include(":features:home:ui")

include(":features:detail:domain")

include(":features:detail:ui")

include(":features:seemore:ui")

include(":features:seemore:domain")

include(":features:favorites:ui")

include(":features:favorites:domain")
