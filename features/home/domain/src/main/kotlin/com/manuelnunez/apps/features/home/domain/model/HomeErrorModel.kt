package com.manuelnunez.apps.features.home.domain.model

sealed class HomeErrorModel {
  data object ServiceError : HomeErrorModel()

  data class GenericError(val error: Throwable) : HomeErrorModel()
}
