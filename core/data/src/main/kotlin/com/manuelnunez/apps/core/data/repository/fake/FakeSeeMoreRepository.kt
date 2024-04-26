package com.manuelnunez.apps.core.data.repository.fake

import androidx.paging.PagingData
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.seemore.domain.repository.SeeMoreRepository
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeSeeMoreRepository @Inject constructor() : SeeMoreRepository {

  override fun getAllItems() = flowOf(PagingData.Companion.from(mockItems))
}
