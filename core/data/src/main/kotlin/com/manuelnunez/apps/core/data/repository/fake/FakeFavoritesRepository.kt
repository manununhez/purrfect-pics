package com.manuelnunez.apps.core.data.repository.fake

import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeFavoritesRepository @Inject constructor() : FavoritesRepository {
  override fun getAllFavorites(): Flow<List<Item>> = flowOf(mockItems)
}
