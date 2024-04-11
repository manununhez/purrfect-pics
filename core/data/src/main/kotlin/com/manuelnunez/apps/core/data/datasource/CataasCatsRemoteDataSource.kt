package com.manuelnunez.apps.core.data.datasource

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.services.dto.CataasResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.service.CataasService
import com.manuelnunez.apps.core.services.util.Result
import javax.inject.Inject

interface CataasCatsRemoteDataSource {
  fun getItems(): Either<List<CataasResponseDTO>, ServiceError>
}

class CataasCatsRemoteDataSourceImpl
@Inject
constructor(private val servicesExecutor: ServicesExecutor, private val apiService: CataasService) :
    CataasCatsRemoteDataSource {

  override fun getItems(): Either<List<CataasResponseDTO>, ServiceError> {
    val response = servicesExecutor.execute(RetrofitServiceRequest(apiService.search()))

    return if (response is Result.Success) eitherSuccess(response.data.data)
    else eitherError((response as Result.Error).exception)
  }
}
