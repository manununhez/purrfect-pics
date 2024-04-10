package com.manuelnunez.apps.core.services.executors

import com.manuelnunez.apps.core.domain.exception.NetworkException
import com.manuelnunez.apps.core.domain.exception.ServiceException
import com.manuelnunez.apps.core.services.util.ServiceResult
import retrofit2.Response

interface ServicesExecutor {

  @Throws(NetworkException::class, ServiceException::class)
  fun <T> execute(request: ServiceRequest<T>): ServiceResult<ServiceResponse<T>>
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

fun <T> Response<T>.toServiceResponse() =
    ServiceResponse(this.body(), this.code(), this.headers().toMultimap())
