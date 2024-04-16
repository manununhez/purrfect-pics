package com.manuelnunez.apps.core.services.executors

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.services.exception.NetworkException
import com.manuelnunez.apps.core.services.exception.ServiceException
import retrofit2.Response

interface ServicesExecutor {

  @Throws(NetworkException::class, ServiceException::class)
  fun <T> execute(request: ServiceRequest<T>): Either<ServiceResponse<T>, ServiceError>
}

interface ServiceRequest<T>

class ServiceResponse<T>(
    val body: T?,
    val statusCode: Int,
    val headers: Map<String, List<String>>,
) {
  val data: T
    get() = body!!
}

class ServiceError(
    val description: String?,
    val statusCode: Int,
    val headers: Map<String, List<String>>,
)

fun <T> Response<T>.toServiceResponse() =
    ServiceResponse(this.body(), this.code(), this.headers().toMultimap())
