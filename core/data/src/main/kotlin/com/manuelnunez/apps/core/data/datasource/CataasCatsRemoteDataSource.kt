package com.manuelnunez.apps.core.data.datasource

import com.manuelnunez.apps.core.services.dto.CatImage
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.service.CataasService
import com.manuelnunez.apps.core.services.util.ServiceResult
import javax.inject.Inject

interface CataasCatsRemoteDataSource {
  fun getItems(): List<CatImage>
}

class CataasCatsRemoteDataSourceImpl
@Inject
constructor(private val servicesExecutor: ServicesExecutor, private val apiService: CataasService) :
    CataasCatsRemoteDataSource {

  override fun getItems(): List<CatImage> {
    val response = servicesExecutor.execute(RetrofitServiceRequest(apiService.search()))

    return if (response is ServiceResult.Success) response.data.data
    else emptyList() // Result.Error(Throwable((response as
    // ServiceResult.Error).exception.description))
  }
}
