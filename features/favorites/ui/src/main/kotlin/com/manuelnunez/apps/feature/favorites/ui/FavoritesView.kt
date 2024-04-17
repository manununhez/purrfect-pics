package com.manuelnunez.apps.feature.favorites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.manuelnunez.apps.core.ui.component.SurfaceText

@Composable
fun FavoritesView() {
  Column(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeContent)) {
    SurfaceText(text = "Favoritos")
  }
}
