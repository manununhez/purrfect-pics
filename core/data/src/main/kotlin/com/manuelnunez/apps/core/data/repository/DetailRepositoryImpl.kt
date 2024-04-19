package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.data.datasource.local.FavoritesDataSource
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRepositoryImpl
@Inject
constructor(private val favoritesDataSource: FavoritesDataSource) : DetailRepository {
  override suspend fun saveFavoriteItem(favoriteItem: Item) {
    favoritesDataSource.addItemToFavorites(favoriteItem)
  }

  override suspend fun removeFavoriteItem(favoriteItem: Item) {
    favoritesDataSource.removeItemFromFavorites(favoriteItem.photoId)
  }

  override fun isItemFavorite(itemPhotoId: String): Flow<Boolean> =
      favoritesDataSource.isItemFavorite(itemPhotoId)
}
