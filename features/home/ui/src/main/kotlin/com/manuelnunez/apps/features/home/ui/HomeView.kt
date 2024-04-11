package com.manuelnunez.apps.features.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelnunez.apps.core.ui.component.DynamicAsyncImage
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.features.home.domain.model.Item
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.FeaturedItemsState
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.PopularItemsState

@Composable
fun HomeView(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit
) {
  val items by viewModel.state.collectAsStateWithLifecycle()

  HomeScreen(items, navigateToDetails)
}

@Composable
private fun HomeScreen(
    items: HomeScreenViewModel.HomeUiState,
    navigateToDetails: (String) -> Unit
) {
  LazyColumn(Modifier.padding(vertical = 10.dp)) {
    featuredSection(items.featuredItemsState)

    item { Spacer(modifier = Modifier.height(30.dp)) }

    popularSection(items.popularItemsState, navigateToDetails)
  }
}

private fun LazyListScope.featuredSection(featuredItems: FeaturedItemsState) {
  when (featuredItems) {
    is FeaturedItemsState.ShowList -> item { FeaturedItem(featuredItems.items) }
    FeaturedItemsState.Loading -> item { LoadingIndicator() }
    else -> {}
  }
}

@Composable
private fun FeaturedItem(items: List<Item>) {
  Column {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = stringResource(id = R.string.section_feature),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge)

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          items(items) { item ->
            Card(modifier = Modifier.size(height = 100.dp, width = 160.dp)) {
              DynamicAsyncImage(
                  modifier = Modifier.fillMaxSize(),
                  imageUrl = item.thumbnailUrl,
                  contentDescription = "")
            }
          }
        }
  }
}

private fun LazyListScope.popularSection(
    popularItems: PopularItemsState,
    navigateToDetails: (String) -> Unit
) {
  when (popularItems) {
    is PopularItemsState.ShowList -> item { PopularItem(popularItems.items, navigateToDetails) }
    PopularItemsState.Loading -> item { LoadingIndicator() }
    else -> {}
  }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun PopularItem(items: List<Item>, navigateToDetails: (String) -> Unit) {
  Column {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = stringResource(id = R.string.section_popular),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge)

    FlowRow(
        modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
          items.forEach { item ->
            Card(modifier = Modifier.height(160.dp).width(100.dp)) {
              DynamicAsyncImage(
                  modifier = Modifier.fillMaxSize(),
                  imageUrl = item.thumbnailUrl,
                  contentDescription = "")
            }
          }

          if (items.size >= 10) { // TODO: check popular size with a Constant?
            Card(
                modifier =
                    Modifier.height(160.dp)
                        .width(100.dp)
                        .clickable(onClick = { navigateToDetails.invoke("") })) {
                  Text(text = "See more")
                }
          }
        }
  }
}

@Composable
private fun LoadingIndicator() {
  Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.tertiary,
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
  MainTheme {
    val items =
        listOf(
            Item("", "", description = "", thumbnailUrl = ""),
            Item("", "", description = "", thumbnailUrl = ""),
            Item("", "", description = "", thumbnailUrl = ""),
            Item("", "", description = "", thumbnailUrl = ""),
            Item("", "", description = "", thumbnailUrl = ""))

    HomeScreen(
        items =
            HomeScreenViewModel.HomeUiState(
                popularItemsState = PopularItemsState.Loading,
                featuredItemsState = FeaturedItemsState.Loading)) {
          //
        }
  }
}
