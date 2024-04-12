package com.manuelnunez.apps.features.home.domain.usecase

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.dispatcher.DispatcherProvider
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.home.domain.model.HomeErrorModel
import com.manuelnunez.apps.features.home.domain.model.Item
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemUseCase
@Inject
constructor(private val homeRepository: HomeRepository, dispatcherProvider: DispatcherProvider) :
    FlowUseCase<Unit, Either<List<Item>, HomeErrorModel>>(dispatcherProvider) {

  override fun execute(input: Unit): Flow<Either<List<Item>, HomeErrorModel>> = flow {
    emit(homeRepository.getAllItems())
  }
}
