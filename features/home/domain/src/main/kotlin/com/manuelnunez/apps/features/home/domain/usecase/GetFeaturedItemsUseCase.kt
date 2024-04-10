package com.manuelnunez.apps.features.home.domain.usecase

import com.manuelnunez.apps.core.domain.usecase.DispatcherProvider
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.home.domain.model.Item
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFeaturedItemsUseCase
@Inject
constructor(private val homeRepository: HomeRepository, dispatcherProvider: DispatcherProvider) :
    FlowUseCase<Unit, List<Item>>(dispatcherProvider) {

  override fun execute(input: Unit): Flow<List<Item>> = flow {
    emit(homeRepository.getFeaturedItems())
  }
}
