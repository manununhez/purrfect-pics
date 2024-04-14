package com.manuelnunez.apps.core.domain.model

sealed class ErrorModel {
  data object ServiceError : ErrorModel()

  data class GenericError(val error: Throwable) : ErrorModel()
}
