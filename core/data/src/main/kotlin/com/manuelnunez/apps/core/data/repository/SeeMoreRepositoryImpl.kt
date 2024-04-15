package com.manuelnunez.apps.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.manuelnunez.apps.core.data.datasource.PexeelsCatsPagingSource
import com.manuelnunez.apps.feature.seemore.domain.repository.SeeMoreRepository
import javax.inject.Inject

class SeeMoreRepositoryImpl @Inject constructor(private val pagingSource: PexeelsCatsPagingSource) :
    SeeMoreRepository {
  override fun getAllItems() =
      Pager(
              config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE),
              pagingSourceFactory = { pagingSource })
          .flow

  companion object {
    private const val PAGE_SIZE = 10
    private const val PREFETCH_DISTANCE = 5
  }
}
