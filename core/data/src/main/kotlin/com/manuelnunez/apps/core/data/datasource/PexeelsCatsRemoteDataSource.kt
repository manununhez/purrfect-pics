package com.manuelnunez.apps.core.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.eitherError
import com.manuelnunez.apps.core.common.eitherSuccess
import com.manuelnunez.apps.core.common.fold
import com.manuelnunez.apps.core.data.PAGE_SIZE
import com.manuelnunez.apps.core.data.PREFETCH_DISTANCE
import com.manuelnunez.apps.core.data.datasource.paging.PexeelsCatsPagingSource
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.services.dto.PexelsSearchResponseDTO
import com.manuelnunez.apps.core.services.executors.RetrofitServiceRequest
import com.manuelnunez.apps.core.services.executors.ServiceError
import com.manuelnunez.apps.core.services.executors.ServicesExecutor
import com.manuelnunez.apps.core.services.service.PexelsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PexelsCatsRemoteDataSource {
  fun getItems(): Either<PexelsSearchResponseDTO, ServiceError>

  fun getAllItems(): Flow<PagingData<Item>>
}

class PexelsCatsRemoteDataSourceImpl
@Inject
constructor(private val servicesExecutor: ServicesExecutor, private val apiService: PexelsService) :
    PexelsCatsRemoteDataSource {

  override fun getItems(): Either<PexelsSearchResponseDTO, ServiceError> {
    val response = servicesExecutor.execute(RetrofitServiceRequest(apiService.searchCats()))

    return response.fold(success = { eitherSuccess(it.data) }, error = { eitherError(it) })
  }

  override fun getAllItems(): Flow<PagingData<Item>> =
      Pager(
              config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
              pagingSourceFactory = { PexeelsCatsPagingSource(apiService) })
          .flow
}
