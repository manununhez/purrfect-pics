package com.manuelnunez.apps.features.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.ui.component.AdaptableVerticalGrid
import com.manuelnunez.apps.core.ui.component.AdaptableVerticalGridDecoration
import com.manuelnunez.apps.core.ui.component.ImageCard
import com.manuelnunez.apps.core.ui.component.TextCard
import com.manuelnunez.apps.core.ui.component.TitleText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.FontScalingPreviews
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.FeaturedItemsState
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.HomeUiState
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.PopularItemsState
import com.manuelnunez.apps.features.home.ui.R
import com.manuelnunez.apps.core.ui.R as RCU

@Composable
fun HomeScreen(
    items: HomeUiState,
    navigateToDetails: (Item) -> Unit,
    navigateToSeeMore: () -> Unit
) {
  LazyColumn(
      modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeContent),
      contentPadding = PaddingValues(vertical = 20.dp)) {
        when (items.featuredItemsState) {
          is FeaturedItemsState.ShowList ->
              item { FeaturedItem(items.featuredItemsState.items, navigateToDetails) }
          FeaturedItemsState.Loading ->
              item {
                LoadingIndicator(
                    loaderContentDescription = stringResource(id = RCU.string.section_feature))
              }
          FeaturedItemsState.Error -> item { FeatureError() }
          else -> {}
        }

        when (items.popularItemsState) {
          is PopularItemsState.ShowList ->
              item {
                PopularItem(items.popularItemsState.items, navigateToDetails, navigateToSeeMore)
              }
          PopularItemsState.Loading ->
              item {
                LoadingIndicator(
                    loaderContentDescription = stringResource(id = RCU.string.section_popular))
              }
          PopularItemsState.Error -> item { PopularError() }
          else -> {}
        }
      }
}

@Composable
private fun FeaturedItem(items: List<Item>, navigateToDetails: (Item) -> Unit) {
  Column {
    TitleText(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp),
        title = stringResource(id = RCU.string.section_feature))

    Spacer(modifier = Modifier.height(10.dp))

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          items(items) { item ->
            ImageCard(
                modifier = Modifier.size(height = 100.dp, width = 160.dp),
                onClick = { navigateToDetails.invoke(item) },
                cardContentDescription = item.description,
                imageUrl = item.thumbnailUrl)
          }
        }

    Spacer(modifier = Modifier.height(30.dp))
  }
}

@Composable
private fun PopularItem(
    items: List<Item>,
    navigateToDetails: (Item) -> Unit,
    navigateToSeeMore: () -> Unit
) {
  val itemWidth = 100.dp
  val itemHeight = 200.dp
  val horizontalMarginItem = 5.dp
  val verticalMarginItem = 10.dp
  val gridPadding = 20.dp - horizontalMarginItem

  Column {
    TitleText(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp),
        title = stringResource(id = RCU.string.section_popular))

    Spacer(modifier = Modifier.height(10.dp))

    AdaptableVerticalGrid(
        modifier = Modifier.padding(horizontal = gridPadding),
        decoration =
            AdaptableVerticalGridDecoration(
                itemHorizontalMargin = 5.dp, itemWidth = itemWidth, gridPadding = gridPadding)) {
          items.forEach { item ->
            ImageCard(
                modifier =
                    Modifier.size(height = itemHeight, width = itemWidth)
                        .padding(horizontal = horizontalMarginItem)
                        .padding(bottom = verticalMarginItem),
                onClick = { navigateToDetails.invoke(item) },
                cardContentDescription = item.description,
                imageUrl = item.thumbnailUrl)
          }

          TextCard(
              modifier =
                  Modifier.size(height = itemHeight, width = itemWidth)
                      .padding(horizontal = horizontalMarginItem)
                      .padding(bottom = verticalMarginItem),
              onClick = navigateToSeeMore,
              text = stringResource(id = R.string.see_more_text))
        }
  }
}

@Composable
private fun LoadingIndicator(loaderContentDescription: String) {
  Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    CircularProgressIndicator(
        modifier = Modifier.semantics { contentDescription = loaderContentDescription },
        color = MaterialTheme.colorScheme.tertiary,
    )
  }
}

@FontScalingPreviews
@ThemePreviews
@Composable
private fun HomeScreenPreview() {
  MainTheme {
    val items = List(5) { Item("", "", description = "", thumbnailUrl = "") }

    HomeScreen(
        items =
            HomeUiState(
                popularItemsState = PopularItemsState.ShowList(items),
                featuredItemsState = FeaturedItemsState.ShowList(items)),
        navigateToDetails = {},
        navigateToSeeMore = {})
  }
}
