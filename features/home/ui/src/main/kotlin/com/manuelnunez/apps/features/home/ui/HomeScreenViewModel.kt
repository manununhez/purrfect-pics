package com.manuelnunez.apps.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelnunez.apps.core.common.fold
import com.manuelnunez.apps.features.home.domain.model.Item
import com.manuelnunez.apps.features.home.domain.usecase.GetFeaturedItemsUseCase
import com.manuelnunez.apps.features.home.domain.usecase.GetPopularItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    private val getFeaturedItemsUseCase: GetFeaturedItemsUseCase,
    private val getPopularItemsUseCase: GetPopularItemsUseCase
) : ViewModel() {

  private val popularItemsState = MutableStateFlow<PopularItemsState>(PopularItemsState.Idle)
  private val featuredItemsState = MutableStateFlow<FeaturedItemsState>(FeaturedItemsState.Idle)

  val state =
      combine(popularItemsState, featuredItemsState) { movieState, dateState ->
            HomeUiState(movieState, dateState)
          }
          .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeUiState.Empty)

  init {
    getItems()
  }

  fun getItems() {
    getPopularItems()
    getFeaturedItems()
  }

  private fun getPopularItems() {
    getPopularItemsUseCase
        .prepare(Unit)
        .onStart { popularItemsState.value = PopularItemsState.Loading }
        .onEach { response ->
          response.fold(
              success = { popularItemsState.value = PopularItemsState.ShowList(it) },
              error = { popularItemsState.value = PopularItemsState.Error })
        }
        .catch { popularItemsState.value = PopularItemsState.Error }
        .launchIn(viewModelScope)
  }

  private fun getFeaturedItems() {
    getFeaturedItemsUseCase
        .prepare(Unit)
        .onStart { featuredItemsState.value = FeaturedItemsState.Loading }
        .onEach { response ->
          response.fold(
              success = { featuredItemsState.value = FeaturedItemsState.ShowList(it) },
              error = { featuredItemsState.value = FeaturedItemsState.Error })
        }
        .catch { featuredItemsState.value = FeaturedItemsState.ShowList(emptyList()) }
        .launchIn(viewModelScope)
  }

  sealed interface PopularItemsState {
    data object Idle : PopularItemsState

    data object Loading : PopularItemsState

    data object Error : PopularItemsState

    data class ShowList(val items: List<Item>) : PopularItemsState
  }

  sealed interface FeaturedItemsState {
    data object Idle : FeaturedItemsState

    data object Loading : FeaturedItemsState

    data object Error : FeaturedItemsState

    data class ShowList(val items: List<Item>) : FeaturedItemsState
  }

  data class HomeUiState(
      val popularItemsState: PopularItemsState = PopularItemsState.Idle,
      val featuredItemsState: FeaturedItemsState = FeaturedItemsState.Idle,
  ) {
    companion object {
      val Empty = HomeUiState()
    }
  }
}
