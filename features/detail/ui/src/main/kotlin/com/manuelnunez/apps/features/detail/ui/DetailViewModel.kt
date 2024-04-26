package com.manuelnunez.apps.features.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.model.toDecodedItem
import com.manuelnunez.apps.features.detail.domain.usecase.GetFavoriteStatusUseCase
import com.manuelnunez.apps.features.detail.domain.usecase.RemoveFavoritesUseCase
import com.manuelnunez.apps.features.detail.domain.usecase.SaveFavoritesUseCase
import com.manuelnunez.apps.features.detail.ui.navigation.DETAIL_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val saveFavoritesUseCase: SaveFavoritesUseCase,
    private val removeFavoritesUseCase: RemoveFavoritesUseCase,
    private val getFavoriteStatusUseCase: GetFavoriteStatusUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val selectedItemString: StateFlow<String> =
      savedStateHandle.getStateFlow(key = DETAIL_ITEM, initialValue = "")

  private val selectedItem: StateFlow<Item> =
      selectedItemString
          .mapLatest { itemString -> itemString.toDecodedItem() }
          .stateIn(
              scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = Item.empty)

  @VisibleForTesting val isItemFavorite = MutableStateFlow(false)

  val state =
      combine(isItemFavorite, selectedItem) { isFavorite, item -> DetailUiState(item, isFavorite) }
          .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DetailUiState.Empty)

  init {
    checkFavorite()
  }

  fun checkFavorite() {
    getFavoriteStatusUseCase
        .prepare(selectedItem.value.photoId)
        .map { isItemFavorite.value = it }
        .catch {}
        .launchIn(viewModelScope)
  }

  fun favorite() {
    if (isItemFavorite.value) {
      removeFavorite(selectedItem.value)
    } else {
      saveFavorite(selectedItem.value)
    }
  }

  fun saveFavorite(item: Item) {
    saveFavoritesUseCase.prepare(item).catch {}.launchIn(viewModelScope)
  }

  fun removeFavorite(item: Item) {
    removeFavoritesUseCase.prepare(item).catch {}.launchIn(viewModelScope)
  }

  data class DetailUiState(
      val item: Item = Item.empty,
      val isFavorite: Boolean = false,
  ) {
    companion object {
      val Empty = DetailUiState()
    }
  }
}
