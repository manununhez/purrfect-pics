package com.manuelnunez.apps.features.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val getFavoritesUseCase: GetFavoritesUseCase) :
    ViewModel() {

  val favoriteItemsState = MutableStateFlow<FavoriteItemsState>(FavoriteItemsState.Idle)

  init {
    getFavorites()
  }

  private fun getFavorites() {
    getFavoritesUseCase
        .prepare(Unit)
        .onStart { favoriteItemsState.value = FavoriteItemsState.Loading }
        .onEach { favoriteItemsState.value = FavoriteItemsState.ShowList(it) }
        .catch { favoriteItemsState.value = FavoriteItemsState.ShowList(emptyList()) }
        .launchIn(viewModelScope)
  }

  sealed interface FavoriteItemsState {
    data object Idle : FavoriteItemsState

    data object Loading : FavoriteItemsState

    data class ShowList(val items: List<Item>) : FavoriteItemsState
  }
}
