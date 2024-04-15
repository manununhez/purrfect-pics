package com.manuelnunez.apps.features.home.domain.usecase

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.dispatcher.DispatcherProvider
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFeaturedItemsUseCase
@Inject
constructor(private val homeRepository: HomeRepository, dispatcherProvider: DispatcherProvider) :
    FlowUseCase<Unit, Either<List<Item>, ErrorModel>>(dispatcherProvider) {

  override fun execute(input: Unit): Flow<Either<List<Item>, ErrorModel>> = flow {
    emit(homeRepository.getFeaturedItems())
  }
}
