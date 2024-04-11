package com.manuelnunez.apps.core.services.util

import com.manuelnunez.apps.core.services.executors.ServiceError

sealed interface Result<out T> {
  data class Success<T>(val data: T) : Result<T>

  data class Error(val exception: ServiceError) : Result<Nothing>
}
