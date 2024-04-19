package com.manuelnunez.apps.features.detail.domain.repository

import com.manuelnunez.apps.core.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
  suspend fun saveFavoriteItem(favoriteItem: Item)

  suspend fun removeFavoriteItem(favoriteItem: Item)

  fun isItemFavorite(itemPhotoId: String): Flow<Boolean>
}
