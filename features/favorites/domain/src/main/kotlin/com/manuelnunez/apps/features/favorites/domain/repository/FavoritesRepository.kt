package com.manuelnunez.apps.features.favorites.domain.repository

import com.manuelnunez.apps.core.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
  fun getAllFavorites(): Flow<List<Item>>
}
