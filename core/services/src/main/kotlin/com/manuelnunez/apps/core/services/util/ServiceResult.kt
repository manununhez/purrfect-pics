package com.manuelnunez.apps.core.services.util

import com.manuelnunez.apps.core.services.executors.ServiceError

sealed interface ServiceResult<out T> {
  data class Success<T>(val data: T) : ServiceResult<T>

  data class Error(val exception: ServiceError) : ServiceResult<Nothing>
}
