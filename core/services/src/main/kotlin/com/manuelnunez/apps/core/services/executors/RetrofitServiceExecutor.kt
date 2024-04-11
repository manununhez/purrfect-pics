package com.manuelnunez.apps.core.services.executors

import com.google.gson.JsonSyntaxException
import com.manuelnunez.apps.core.services.util.Result
import retrofit2.Call

class RetrofitServicesExecutor : ServicesExecutor {

  override fun <T> execute(request: ServiceRequest<T>): Result<ServiceResponse<T>> {
    if (request !is RetrofitServiceRequest<T>) {
      throw IllegalArgumentException("RetrofitServicesExecutor only accepts RetrofitServiceRequest")
    }

    return try {
      val response = request.retrofitCall.execute()
      if (response.isSuccessful) {
        Result.Success(
            ServiceResponse(response.body(), response.code(), response.headers().toMultimap()))
      } else {
        Result.Error(
            ServiceError(
                response.errorBody()?.string(), response.code(), response.headers().toMultimap()))
      }
    } catch (ex: JsonSyntaxException) {
      Result.Error(ServiceError("Json data parsing error: ${ex.message}", -1, emptyMap()))
    } catch (e: Exception) {
      Result.Error(ServiceError("Unknown error:  ${e.message}", -1, emptyMap()))
    }
  }
}

class RetrofitServiceRequest<T>(val retrofitCall: Call<T>) : ServiceRequest<T>
