package com.manuelnunez.apps.feature.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.domain.usecase.GetFavoritesUseCase
import com.manuelnunez.apps.features.favorites.domain.usecase.RemoveFavoritesUseCase
import com.manuelnunez.apps.features.favorites.domain.usecase.SaveFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val saveFavoritesUseCase: SaveFavoritesUseCase,
    private val removeFavoritesUseCase: RemoveFavoritesUseCase
) : ViewModel() {

  val favoriteItemsState = MutableStateFlow<FavoriteItemsState>(FavoriteItemsState.Idle)

  init {
    getFavoritesUseCase
        .prepare(Unit)
        .onStart { favoriteItemsState.value = FavoriteItemsState.Loading }
        .onEach { favoriteItemsState.value = FavoriteItemsState.ShowList(it) }
        .catch { favoriteItemsState.value = FavoriteItemsState.Error }
        .launchIn(viewModelScope)
  }

  fun saveFavorite(item: Item) {
    saveFavoritesUseCase.prepare(item).catch {}.launchIn(viewModelScope)
  }

    fun removeFavorite(item: Item) {
        removeFavoritesUseCase.prepare(item).catch {}.launchIn(viewModelScope)
    }

  sealed interface FavoriteItemsState {
    data object Idle : FavoriteItemsState

    data object Loading : FavoriteItemsState

    data object Error : FavoriteItemsState

    data class ShowList(val items: List<Item>) : FavoriteItemsState
  }
}
