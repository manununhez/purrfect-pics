package com.manuelnunez.apps.features.home.domain.usecase

import com.manuelnunez.apps.core.domain.usecase.DispatcherProvider
import com.manuelnunez.apps.core.domain.usecase.FlowUseCase
import com.manuelnunez.apps.features.home.domain.model.SearchResponse
import com.manuelnunez.apps.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase
@Inject
constructor(private val homeRepository: HomeRepository, dispatcherProvider: DispatcherProvider) :
    FlowUseCase<Unit, SearchResponse>(dispatcherProvider) {

  override fun execute(input: Unit): Flow<SearchResponse> = flow { emit(homeRepository.search()) }
}
