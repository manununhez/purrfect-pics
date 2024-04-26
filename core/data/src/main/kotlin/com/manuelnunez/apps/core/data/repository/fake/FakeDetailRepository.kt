package com.manuelnunez.apps.core.data.repository.fake

import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeDetailRepository @Inject constructor() : DetailRepository {
  private val items = mutableListOf<Item>()

  override suspend fun saveFavoriteItem(favoriteItem: Item) {
    items.add(favoriteItem)
  }

  override suspend fun removeFavoriteItem(favoriteItem: Item) {
    items.remove(favoriteItem)
  }

  override fun isItemFavorite(itemPhotoId: String): Flow<Boolean> = flowOf(true)
}
