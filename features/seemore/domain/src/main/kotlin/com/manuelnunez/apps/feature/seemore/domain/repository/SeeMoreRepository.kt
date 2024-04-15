package com.manuelnunez.apps.feature.seemore.domain.repository

import androidx.paging.PagingData
import com.manuelnunez.apps.core.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface SeeMoreRepository {
  fun getAllItems(): Flow<PagingData<Item>>
}
