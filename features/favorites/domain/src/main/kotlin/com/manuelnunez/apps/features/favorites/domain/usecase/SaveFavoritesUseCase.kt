package com.manuelnunez.apps.features.favorites.domain.usecase

import com.manuelnunez.apps.core.common.dispatcher.CoroutineDispatcherProvider
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveFavoritesUseCase
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : FlowUseCase<Item, Unit>(coroutineDispatcherProvider) {

  override fun execute(input: Item): Flow<Unit> = flow {
    favoritesRepository.saveFavoriteItem(input)
  }
}
