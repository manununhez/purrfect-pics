package com.manuelnunez.apps.features.seemore.domain.usecase

import androidx.paging.PagingData
import com.manuelnunez.apps.core.common.dispatcher.CoroutineDispatcherProvider
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.seemore.domain.repository.SeeMoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllItemUseCase
@Inject
constructor(
    private val seeMoreRepository: SeeMoreRepository,
    coroutineDispatcherProvider: CoroutineDispatcherProvider
) : FlowUseCase<Unit, PagingData<Item>>(coroutineDispatcherProvider) {

  override fun execute(input: Unit): Flow<PagingData<Item>> = seeMoreRepository.getAllItems()
}
