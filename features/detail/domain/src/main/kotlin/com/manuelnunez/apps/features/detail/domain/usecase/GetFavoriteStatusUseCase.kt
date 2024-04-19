package com.manuelnunez.apps.features.detail.domain.usecase

import com.manuelnunez.apps.core.common.dispatcher.CoroutineDispatcherProvider
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteStatusUseCase
@Inject
constructor(
    private val favoritesRepository: DetailRepository,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : FlowUseCase<String, Boolean>(coroutineDispatcherProvider) {

  override fun execute(input: String): Flow<Boolean> = favoritesRepository.isItemFavorite(input)
}
