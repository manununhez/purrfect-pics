package com.manuelnunez.apps.core.data

import com.manuelnunez.apps.core.data.datasource.CatsRemoteDataSource
import com.manuelnunez.apps.core.services.PexelsService
import com.manuelnunez.apps.core.services.dto.SearchResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.util.ServiceResult
import javax.inject.Inject

class CatsRemoteDataSourceImpl
@Inject
constructor(private val servicesExecutor: ServicesExecutor, private val apiService: PexelsService) :
    CatsRemoteDataSource {

  override fun search(): SearchResponseDTO {
    val response = servicesExecutor.execute(RetrofitServiceRequest(apiService.search()))

    return if (response is ServiceResult.Success) response.data.data
    else SearchResponseDTO(0, 0, 0, emptyList()) // Result.Error(Throwable((response as
    // ServiceResult.Error).exception.description))
  }
}
