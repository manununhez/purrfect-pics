package com.manuelnunez.apps.feature.seemore.domain.usecase

import com.manuelnunez.apps.core.common.Either
import com.manuelnunez.apps.core.common.dispatcher.DispatcherProvider
import com.manuelnunez.apps.core.domain.model.ErrorModel
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.feature.seemore.domain.repository.SeeMoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllItemUseCase
@Inject
constructor(
    private val seeMoreRepository: SeeMoreRepository,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<Unit, Either<List<Item>, ErrorModel>>(dispatcherProvider) {

  override fun execute(input: Unit): Flow<Either<List<Item>, ErrorModel>> = flow {
    emit(seeMoreRepository.getAllItems())
  }
}
