package com.manuelnunez.apps.core.data.repository

import com.manuelnunez.apps.core.data.datasource.local.FavoritesDataSource
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl
@Inject
constructor(private val favoritesDataSource: FavoritesDataSource) : FavoritesRepository {
  override fun getAllFavorites(): Flow<List<Item>> = favoritesDataSource.favorites

  override suspend fun saveFavoriteItem(favoriteItem: Item) {
    favoritesDataSource.addItemToFavorites(favoriteItem)
  }

  override suspend fun removeFavoriteItem(favoriteItem: Item) {
    favoritesDataSource.removeItemFromFavorites(favoriteItem.photoId)
  }
}
