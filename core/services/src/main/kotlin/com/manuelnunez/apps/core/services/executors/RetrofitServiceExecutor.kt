package com.manuelnunez.apps.core.services.executors

import com.google.gson.JsonSyntaxException
import com.manuelnunez.apps.core.domain.network.NetworkManager
import com.manuelnunez.apps.core.services.util.ServiceResult
import retrofit2.Call
import javax.inject.Inject

class RetrofitServicesExecutor @Inject constructor(private val networkManager: NetworkManager) :
    ServicesExecutor {

  override fun <T> execute(request: ServiceRequest<T>): ServiceResult<ServiceResponse<T>> {
    if (request !is RetrofitServiceRequest<T>) {
      throw IllegalArgumentException("RetrofitServicesExecutor only accepts RetrofitServiceRequest")
    }

    return try {
      networkManager.checkConnectivity()

      val response = request.retrofitCall.execute()
      if (response.isSuccessful) {
        ServiceResult.Success(
            ServiceResponse(response.body(), response.code(), response.headers().toMultimap()))
      } else {
        ServiceResult.Error(
            ServiceError(
                response.errorBody()?.string(), response.code(), response.headers().toMultimap()))
      }
    } catch (ex: JsonSyntaxException) {
      ServiceResult.Error(ServiceError("Json data parsing error: ${ex.message}", -1, emptyMap()))
    } catch (e: Exception) {
      ServiceResult.Error(ServiceError("Unknown error:  ${e.message}", -1, emptyMap()))
    }
  }
}

class RetrofitServiceRequest<T>(val retrofitCall: Call<T>) : ServiceRequest<T>
