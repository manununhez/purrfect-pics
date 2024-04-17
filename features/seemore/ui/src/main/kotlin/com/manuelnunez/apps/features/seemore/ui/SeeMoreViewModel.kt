package com.manuelnunez.apps.features.seemore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.seemore.domain.usecase.GetAllItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SeeMoreViewModel @Inject constructor(private val getAllItemUseCase: GetAllItemUseCase) :
    ViewModel() {
  private val _itemsPagingDataFlow = MutableStateFlow<PagingData<Item>>(PagingData.empty())
  val itemsPagingDataFlow: Flow<PagingData<Item>> = _itemsPagingDataFlow

  init {
    loadItems()
  }

  private fun loadItems() {
    getAllItemUseCase
        .prepare(Unit)
        .cachedIn(viewModelScope)
        .map { pagingData -> _itemsPagingDataFlow.value = pagingData }
        .launchIn(viewModelScope)
  }

  fun retry() {
    loadItems()
  }
}
