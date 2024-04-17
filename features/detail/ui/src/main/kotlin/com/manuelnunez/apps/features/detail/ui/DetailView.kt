package com.manuelnunez.apps.features.detail.ui

import androidx.compose.runtime.Composable
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.detail.ui.components.DetailErrorScreen
import com.manuelnunez.apps.features.detail.ui.components.DetailScreen

@Composable
fun DetailView(onBackClick: () -> Unit, item: Item?) {
  if (item == null) {
    DetailErrorScreen(onBackClick)
  } else {
    DetailScreen(item, onBackClick)
  }
}
