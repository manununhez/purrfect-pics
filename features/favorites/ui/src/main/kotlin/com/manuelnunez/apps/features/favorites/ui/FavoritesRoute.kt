package com.manuelnunez.apps.features.favorites.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.ui.FavoritesViewModel.FavoriteItemsState
import com.manuelnunez.apps.features.favorites.ui.component.FavoritesErrorScreen
import com.manuelnunez.apps.features.favorites.ui.component.FavoritesScreen

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onFavoriteClicked: (Item) -> Unit,
    onBackClick: () -> Unit
) {
  val items by viewModel.favoriteItemsState.collectAsStateWithLifecycle()

  when (items) {
    is FavoriteItemsState.Loading ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          CircularProgressIndicator()
        }
    is FavoriteItemsState.ShowList ->
        FavoritesScreen(
            items = (items as FavoriteItemsState.ShowList).items,
            navigateToDetails = onFavoriteClicked,
            onBackClick = onBackClick)
    is FavoriteItemsState.Error -> FavoritesErrorScreen(onBackClick = onBackClick)
    else -> {}
  }
}
