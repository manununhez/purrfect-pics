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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.ui.component.ImageCard
import com.manuelnunez.apps.core.ui.component.TitleText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.FontScalingPreviews
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
import kotlinx.coroutines.flow.flowOf
import com.manuelnunez.apps.core.ui.R as RCU

@Composable
fun SeeMoreScreen(
    items: LazyPagingItems<Item>,
    onBackClick: () -> Unit,
    navigateToDetails: (Item) -> Unit
) {
  Column(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeContent)) {
    Spacer(modifier = Modifier.height(20.dp))

    SeeMoreToolbar(onBackClick)

    PopularItems(navigateToDetails = navigateToDetails, itemsPagingItems = items)

    Spacer(modifier = Modifier.height(20.dp))
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
      modifier = Modifier.fillMaxSize(),
      state = gridState,
      contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp),
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
    IconButton(onClick = onBackClick) {
      Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
          contentDescription = stringResource(id = RCU.string.button_back),
          tint = MaterialTheme.colorScheme.onSurface)
    }

    TitleText(title = stringResource(id = RCU.string.section_popular))
  }
}

@FontScalingPreviews
@ThemePreviews
@Composable
private fun SeeMoreScreenPreview() {
  MainTheme {
    val items =
        PagingData.from(
            List(5) { index ->
              Item(
                  "$index",
                  "https://example.com/$index",
                  description = "description: $index",
                  thumbnailUrl = "https://example.com/$index")
            })

    SeeMoreScreen(
        items = flowOf(items).collectAsLazyPagingItems(), onBackClick = {}, navigateToDetails = {})
  }
}
