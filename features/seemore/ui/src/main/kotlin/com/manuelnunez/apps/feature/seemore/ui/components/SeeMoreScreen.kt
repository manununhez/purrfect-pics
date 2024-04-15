package com.manuelnunez.apps.feature.seemore.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.ui.component.ImageCard
import com.manuelnunez.apps.core.ui.component.TitleText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.FontScalingPreviews
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
import com.manuelnunez.apps.core.ui.R as RCU

@Composable
fun SeeMoreScreen(
    items: LazyPagingItems<Item>,
    onBackClick: () -> Unit,
    navigateToDetails: (Item) -> Unit
) {
  Column(Modifier.fillMaxSize()) {
    Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))

    SeeMoreToolbar(onBackClick)

    PopularItems(navigateToDetails = navigateToDetails, itemsPagingItems = items)

    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
  }
}

@Composable
fun PopularItems(itemsPagingItems: LazyPagingItems<Item>, navigateToDetails: (Item) -> Unit) {
  val gridState = rememberLazyGridState()

  val cellConfiguration =
      if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        GridCells.Adaptive(minSize = 100.dp)
      } else GridCells.Fixed(3)

  LazyVerticalGrid(
      columns = cellConfiguration,
      modifier = Modifier.imePadding().fillMaxSize(),
      state = gridState,
      contentPadding = PaddingValues(horizontal = 20.dp, vertical = 40.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(
            count = itemsPagingItems.itemCount,
            key = itemsPagingItems.itemKey { it.photoId },
        ) { index ->
          val item = itemsPagingItems[index]
          if (item != null) {
            ImageCard(
                modifier = Modifier.size(width = 100.dp, height = 200.dp),
                imageUrl = item.imageUrl,
                cardContentDescription = item.description,
                onClick = { navigateToDetails.invoke(item) })
          }
        }

        item {
          if (itemsPagingItems.loadState.append is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
          }
        }
      }
}

@Composable
private fun SeeMoreToolbar(onBackClick: () -> Unit) {
  Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    IconButton(onClick = { onBackClick() }) {
      Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
          contentDescription = stringResource(id = RCU.string.button_back),
          tint = MaterialTheme.colorScheme.onSurface)
    }

    TitleText(title = "Popular")
  }
}

@FontScalingPreviews
@ThemePreviews
@Composable
private fun SeeMoreScreenPreview() {
  MainTheme {
    val items = PagingData.from(List(5) { Item("", "", description = "", thumbnailUrl = "") })

    //    SeeMoreScreen(items = items, onBackClick = {}, navigateToDetails = {}) {}
  }
}
