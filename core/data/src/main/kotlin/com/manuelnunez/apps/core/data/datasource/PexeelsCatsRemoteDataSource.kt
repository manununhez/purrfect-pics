package com.manuelnunez.apps.core.data.datasource

import com.manuelnunez.apps.core.services.dto.SearchResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.service.PexelsService
import com.manuelnunez.apps.core.services.util.ServiceResult
import javax.inject.Inject

interface PexelsCatsRemoteDataSource {
  fun getItems(): SearchResponseDTO
}

class PexelsCatsRemoteDataSourceImpl
@Inject
constructor(private val servicesExecutor: ServicesExecutor, private val apiService: PexelsService) :
    PexelsCatsRemoteDataSource {

  override fun getItems(): SearchResponseDTO {
    val response = servicesExecutor.execute(RetrofitServiceRequest(apiService.searchCats()))

    return if (response is ServiceResult.Success) response.data.data
    else SearchResponseDTO(0, 0, 0, emptyList()) // Result.Error(Throwable((response as
    // ServiceResult.Error).exception.description)) //TODO: complete errors
  }
}
