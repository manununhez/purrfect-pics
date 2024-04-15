package com.manuelnunez.apps.feature.seemore.domain.usecase

import androidx.paging.PagingData
import com.manuelnunez.apps.core.common.dispatcher.DispatcherProvider
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.feature.seemore.domain.repository.SeeMoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllItemUseCase
@Inject
constructor(
    private val seeMoreRepository: SeeMoreRepository,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<Unit, PagingData<Item>>(dispatcherProvider) {

  override fun execute(input: Unit): Flow<PagingData<Item>> = seeMoreRepository.getAllItems()
}
