package com.manuelnunez.apps.core.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.data.PAGE_SIZE
import com.manuelnunez.apps.core.data.PREFETCH_DISTANCE
import com.manuelnunez.apps.core.data.datasource.paging.CataasCatsPagingSource
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.services.dto.CataasResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.service.CataasService
import com.manuelnunez.apps.core.services.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CataasCatsRemoteDataSource {
  fun getItems(): Either<List<CataasResponseDTO>, ServiceError>

  fun getAllItems(): Flow<PagingData<Item>>
}

class CataasCatsRemoteDataSourceImpl
@Inject
constructor(private val servicesExecutor: ServicesExecutor, private val apiService: CataasService) :
    CataasCatsRemoteDataSource {

  override fun getItems(): Either<List<CataasResponseDTO>, ServiceError> {
    val response = servicesExecutor.execute(RetrofitServiceRequest(apiService.searchCats()))

    return if (response is Result.Success) eitherSuccess(response.data.data)
    else eitherError((response as Result.Error).exception)
  }

  override fun getAllItems(): Flow<PagingData<Item>> =
      Pager(
              config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
              pagingSourceFactory = { CataasCatsPagingSource(apiService) })
          .flow
}
