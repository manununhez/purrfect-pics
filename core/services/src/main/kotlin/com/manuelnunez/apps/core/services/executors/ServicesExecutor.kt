package com.manuelnunez.apps.core.services.executors

import com.manuelnunez.apps.core.services.exception.NetworkException
import com.manuelnunez.apps.core.services.exception.ServiceException
import com.manuelnunez.apps.core.services.util.Result

interface ServicesExecutor {

  @Throws(NetworkException::class, ServiceException::class)
  fun <T> execute(request: ServiceRequest<T>): Result<ServiceResponse<T>>
}

interface ServiceRequest<T>

open class ServiceResponse<T>(
    val optData: T?,
    val statusCode: Int,
    val headers: Map<String, List<String>>,
) {
  val data: T
    get() = optData!!
}

open class ServiceError(
    val description: String?,
    val statusCode: Int,
    val headers: Map<String, List<String>>,
)
