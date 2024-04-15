package com.manuelnunez.apps.core.services.executors

import com.google.gson.JsonSyntaxException
import com.manuelnunez.apps.core.services.exception.NetworkException
import com.manuelnunez.apps.core.services.util.Result
import retrofit2.Call
import java.util.concurrent.TimeoutException

class ServiceExecutorRetrofitImpl : ServicesExecutor {

  override fun <T> execute(request: ServiceRequest<T>): Result<ServiceResponse<T>> {
    if (request !is RetrofitServiceRequest<T>) {
      throw IllegalArgumentException("Accepted only RetrofitServiceRequest")
    }

    return try {
      val response = request.retrofitCall.execute()
      if (response.isSuccessful) {
        Result.Success(
            ServiceResponse(
                optData = response.body(),
                statusCode = response.code(),
                headers = response.headers().toMultimap()))
      } else {
        Result.Error(
            ServiceError(
                description = response.errorBody()?.string(),
                statusCode = response.code(),
                headers = response.headers().toMultimap()))
      }
    } catch (ex: TimeoutException) {
      Result.Error(ServiceError("Timeout error: ${ex.message}", -1, emptyMap()))
    } catch (ex: NetworkException) {
      Result.Error(ServiceError("Network error: ${ex.message}", -1, emptyMap()))
    } catch (ex: JsonSyntaxException) {
      Result.Error(ServiceError("Json data parsing error: ${ex.message}", -1, emptyMap()))
    } catch (e: Exception) {
      Result.Error(ServiceError("Unknown error:  ${e.message}", -1, emptyMap()))
    }
  }
}

class RetrofitServiceRequest<T>(val retrofitCall: Call<T>) : ServiceRequest<T>
