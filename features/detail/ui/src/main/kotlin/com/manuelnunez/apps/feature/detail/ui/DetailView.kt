package com.manuelnunez.apps.feature.detail.ui

import androidx.compose.runtime.Composable
import com.manuelnunez.apps.feature.detail.ui.components.DetailErrorScreen
import com.manuelnunez.apps.feature.detail.ui.components.DetailScreen
import com.manuelnunez.apps.features.home.domain.model.Item

@Composable
fun DetailView(onBackClick: () -> Unit, item: Item?) {
  item?.let { DetailScreen(item, onBackClick) }
      ?: run {
        DetailErrorScreen(onBackClick) // TODO:
      }
}
