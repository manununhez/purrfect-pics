package com.manuelnunez.apps.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelnunez.apps.features.home.domain.model.SearchResponse
import com.manuelnunez.apps.features.home.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val searchUseCase: SearchUseCase) :
    ViewModel() {

  private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
  val uiState: StateFlow<HomeUiState> = _uiState

  init {
    search()
  }

  fun search() {
    searchUseCase
        .prepare(Unit)
        .onEach { response -> _uiState.value = HomeUiState.Success(response) }
        .launchIn(viewModelScope)
  }

  sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Error(val throwable: Throwable) : HomeUiState

    data class Success(val data: SearchResponse) : HomeUiState
  }
}
