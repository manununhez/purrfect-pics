package com.manuelnunez.apps.feature.seemore.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.ui.component.AdaptableVerticalGrid
import com.manuelnunez.apps.core.ui.component.AdaptableVerticalGridDecoration
import com.manuelnunez.apps.core.ui.component.ImageCard
import com.manuelnunez.apps.features.seemore.ui.R

@Composable
fun SeeMoreView(onBackClick: () -> Unit, navigateToDetails: (Item) -> Unit) {

  SeeMoreScreen(onBackClick, navigateToDetails)
}

@Composable
fun SeeMoreScreen(onBackClick: () -> Unit, navigateToDetails: (Item) -> Unit) {
  val testItems =
      listOf(
          Item(
              "123123",
              "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png",
              "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png",
              "Test"))

  LazyColumn {
    item { Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing)) }

    item { SeeMoreToolbar(onBackClick) }

    item { Items(navigateToDetails = navigateToDetails, items = testItems) }

    item { Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing)) }
  }
}

@Composable
fun Items(items: List<Item>, navigateToDetails: (Item) -> Unit) {
  val itemWidth = 100.dp
  val itemHeight = 200.dp
  val horizontalMarginItem = 5.dp
  val verticalMarginItem = 10.dp
  val gridPadding = 20.dp - horizontalMarginItem

  AdaptableVerticalGrid(
      decoration =
          AdaptableVerticalGridDecoration(
              itemHorizontalMargin = 5.dp, itemWidth = itemWidth, gridPadding = gridPadding)) {
        items.forEach {
          ImageCard(
              imageUrl = it.imageUrl,
              cardContentDescription = it.description,
              onClick = { navigateToDetails.invoke(it) })
        }
      }
}

@Composable
private fun SeeMoreToolbar(onBackClick: () -> Unit) {
  IconButton(onClick = { onBackClick() }) {
    Icon(
        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
        contentDescription = stringResource(id = R.string.button_back),
        tint = MaterialTheme.colorScheme.onSurface)
  }
}
