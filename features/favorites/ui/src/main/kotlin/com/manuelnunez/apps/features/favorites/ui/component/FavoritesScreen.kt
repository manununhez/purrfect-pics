package com.manuelnunez.apps.features.favorites.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.ui.component.ImageCard
import com.manuelnunez.apps.core.ui.component.SurfaceText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.OrientationPreviews
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
import com.manuelnunez.apps.features.favorites.ui.R

@Composable
fun FavoritesScreen(items: List<Item>, navigateToDetails: (Item) -> Unit, onBackClick: () -> Unit) {
  Column(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeContent)) {
    Spacer(modifier = Modifier.height(20.dp))

    SurfaceText(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp),
        text = stringResource(id = R.string.section_favorites))

    FavoriteItems(navigateToDetails = navigateToDetails, favoriteItems = items)

    Spacer(modifier = Modifier.height(20.dp))
  }
}

@Composable
private fun FavoriteItems(navigateToDetails: (Item) -> Unit, favoriteItems: List<Item>) {
  val gridState = rememberLazyGridState()

  val cellConfiguration =
      if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        GridCells.Adaptive(minSize = 100.dp)
      } else GridCells.Fixed(3)

  if (favoriteItems.isEmpty()) {
    Text(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        color = MaterialTheme.colorScheme.onSurface,
        text = "Add your favorite cats, and they will be display here! ")
  } else {
    LazyVerticalGrid(
        columns = cellConfiguration,
        modifier = Modifier.fillMaxSize(),
        state = gridState,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          items(items = favoriteItems) { item ->
            ImageCard(
                modifier = Modifier.size(width = 100.dp, height = 200.dp),
                imageUrl = item.imageUrl,
                cardContentDescription = item.description,
                onClick = { navigateToDetails.invoke(item) })
          }
        }
  }
}

@ThemePreviews
@OrientationPreviews
@Composable
fun FavoriteScreenEmptyPreview() {
  MainTheme { FavoritesScreen(items = emptyList(), navigateToDetails = {}, onBackClick = {}) }
}

@ThemePreviews
@OrientationPreviews
@Composable
fun FavoriteScreenPreview() {
  MainTheme {
    val items =
        List(5) { index ->
          Item(
              "$index",
              "https://example.com/$index",
              description = "description: $index",
              thumbnailUrl = "https://example.com/$index")
        }
    FavoritesScreen(items = items, navigateToDetails = {}, onBackClick = {})
  }
}
