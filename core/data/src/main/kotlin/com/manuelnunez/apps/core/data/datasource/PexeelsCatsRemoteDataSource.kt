package com.manuelnunez.apps.core.data.datasource

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.services.dto.PexelsSearchResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.service.PexelsService
import com.manuelnunez.apps.core.services.util.Result
import javax.inject.Inject

interface PexelsCatsRemoteDataSource {
  fun getItems(): Either<PexelsSearchResponseDTO, ServiceError>
}

class PexelsCatsRemoteDataSourceImpl
@Inject
constructor(private val servicesExecutor: ServicesExecutor, private val apiService: PexelsService) :
    PexelsCatsRemoteDataSource {

  override fun getItems(): Either<PexelsSearchResponseDTO, ServiceError> {
    val response = servicesExecutor.execute(RetrofitServiceRequest(apiService.searchCats()))

    return if (response is Result.Success) eitherSuccess(response.data.data)
    else eitherError((response as Result.Error).exception)
  }
}
