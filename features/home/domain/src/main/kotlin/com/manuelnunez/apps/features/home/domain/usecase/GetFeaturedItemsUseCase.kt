package com.manuelnunez.apps.features.home.domain.usecase

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.dispatcher.CoroutineDispatcherProvider
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFeaturedItemsUseCase
@Inject
constructor(
    private val homeRepository: HomeRepository,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : FlowUseCase<Unit, Either<List<Item>, ErrorModel>>(coroutineDispatcherProvider) {

  override fun execute(input: Unit): Flow<Either<List<Item>, ErrorModel>> = flow {
    emit(homeRepository.getFeaturedItems())
  }
}
