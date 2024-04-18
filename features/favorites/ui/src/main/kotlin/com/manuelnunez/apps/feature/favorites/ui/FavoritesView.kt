package com.manuelnunez.apps.feature.favorites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.ui.component.SurfaceText
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun FavoritesView(viewModel: FavoritesViewModel = hiltViewModel()) {
  val items by viewModel.favoriteItemsState.collectAsStateWithLifecycle()
  Column(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeContent)) {
    Button(
        onClick = {
          val currentDateTime = Calendar.getInstance().time

          // Define a date-time formatter (optional)
          val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

          // Format the current date and time using the formatter (optional)
          val formattedDateTime = formatter.format(currentDateTime)
          viewModel.saveFavorite(Item(formattedDateTime, "", "", ""))
        }) {}

    Button(
      onClick = {
        val currentDateTime = Calendar.getInstance().time

        // Define a date-time formatter (optional)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        // Format the current date and time using the formatter (optional)
        val formattedDateTime = formatter.format(currentDateTime)
        viewModel.removeFavorite(Item(formattedDateTime, "", "", ""))
      }) {}
    if (items is FavoritesViewModel.FavoriteItemsState.ShowList) {
      (items as FavoritesViewModel.FavoriteItemsState.ShowList).items.forEach {
        SurfaceText(text = it.photoId)
      }
    }
  }
}
