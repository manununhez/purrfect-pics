package com.manuelnunez.apps.features.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelnunez.apps.features.detail.ui.components.DetailScreen

@Composable
fun DetailRoute(detailViewModel: DetailViewModel = hiltViewModel(), onBackClick: () -> Unit) {
  val item by detailViewModel.state.collectAsStateWithLifecycle()
  DetailScreen(
      item = item.item,
      isFavorite = item.isFavorite,
      onFavoriteClicked = detailViewModel::favorite,
      onBackClick = onBackClick)
}
