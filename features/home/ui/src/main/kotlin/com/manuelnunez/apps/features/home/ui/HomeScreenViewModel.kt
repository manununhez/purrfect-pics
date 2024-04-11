package com.manuelnunez.apps.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelnunez.apps.core.common.fold
import com.manuelnunez.apps.features.home.domain.model.Item
import com.manuelnunez.apps.features.home.domain.usecase.GetItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val getItemUseCase: GetItemUseCase) :
    ViewModel() {

  private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
  val uiState: StateFlow<HomeUiState> = _uiState

  init {
    search()
  }

  fun search() {
    getItemUseCase
        .prepare(Unit)
        .onEach { response ->
          response.fold(
              success = { _uiState.value = HomeUiState.Success(it) },
              error = { _uiState.value = HomeUiState.Error })
        }
        .catch { _uiState.value = HomeUiState.Error }
        .launchIn(viewModelScope)
  }

  sealed interface HomeUiState {
    data object Loading : HomeUiState

    data object Error : HomeUiState

    data class Success(val data: List<Item>) : HomeUiState
  }
}
