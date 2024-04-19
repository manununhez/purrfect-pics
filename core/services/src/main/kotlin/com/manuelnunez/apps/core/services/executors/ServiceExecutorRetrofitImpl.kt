package com.manuelnunez.apps.core.services.executors

import com.google.gson.JsonSyntaxException
import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.services.exception.NetworkException
import retrofit2.Call
import java.util.concurrent.TimeoutException

class ServiceExecutorRetrofitImpl : ServicesExecutor {

  override fun <T> execute(request: ServiceRequest<T>): Either<ServiceResponse<T>, ServiceError> {
    if (request !is RetrofitServiceRequest<T>) {
      throw IllegalArgumentException("Accepted only RetrofitServiceRequest")
    }

    return try {
      val response = request.retrofitCall.execute()
      if (response.isSuccessful) {
        eitherSuccess(
            ServiceResponse(
                body = response.body(),
                statusCode = response.code(),
                headers = response.headers().toMultimap()))
      } else {
        eitherError(
            ServiceError(
                description = response.errorBody()?.string(),
                statusCode = response.code(),
                headers = response.headers().toMultimap()))
      }
    } catch (ex: TimeoutException) {
      eitherError(ServiceError("Timeout error: ${ex.message}", -1, emptyMap()))
    } catch (ex: NetworkException) {
      eitherError(ServiceError("Network error: ${ex.message}", -1, emptyMap()))
    } catch (ex: JsonSyntaxException) {
      eitherError(ServiceError("Json data parsing error: ${ex.message}", -1, emptyMap()))
    } catch (e: Exception) {
      eitherError(ServiceError("Unknown error:  ${e.message}", -1, emptyMap()))
    }
  }
}

class RetrofitServiceRequest<T>(val retrofitCall: Call<T>) : ServiceRequest<T>
