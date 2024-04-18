package com.manuelnunez.apps.features.favorites.domain.usecase

import com.manuelnunez.apps.core.common.dispatcher.CoroutineDispatcherProvider
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase
@Inject
constructor(
    private val favoritesRepository: FavoritesRepository,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : FlowUseCase<Unit, List<Item>>(coroutineDispatcherProvider) {

  override fun execute(input: Unit): Flow<List<Item>> = favoritesRepository.getAllFavorites()
}
