package com.manuelnunez.apps.features.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
    navigateToDetails: () -> Unit,
    navigateToSeeMore: () -> Unit
) {
  val items by viewModel.state.collectAsStateWithLifecycle()

  if (items.popularItemsState is PopularItemsState.Error &&
      items.featuredItemsState is FeaturedItemsState.Error) {
    HomeErrorScreen { viewModel.getItems() }
  } else {
    HomeScreen(items, navigateToDetails, navigateToSeeMore)
  }
}

@Composable
private fun HomeErrorScreen(retry: () -> Unit) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column {
      Button(
          onClick = { retry.invoke() },
          colors =
              ButtonDefaults.buttonColors(
                  containerColor = MaterialTheme.colorScheme.onBackground,
              )) {
            Text(text = stringResource(id = R.string.button_retry))
          }

      Text(text = stringResource(id = R.string.alert_error_try_again))
    }
  }
}

@Composable
fun HomeScreen(
    items: HomeScreenViewModel.HomeUiState,
    navigateToDetails: () -> Unit,
    navigateToSeeMore: () -> Unit
) {
  LazyColumn(Modifier.padding(vertical = 20.dp)) {
    featuredSection(items.featuredItemsState, navigateToDetails)

    item { Spacer(modifier = Modifier.height(30.dp)) }

    popularSection(items.popularItemsState, navigateToDetails, navigateToSeeMore)
  }
}

private fun LazyListScope.featuredSection(
    featuredItems: FeaturedItemsState,
    navigateToDetails: () -> Unit
) {
  when (featuredItems) {
    is FeaturedItemsState.ShowList -> item { FeaturedItem(featuredItems.items, navigateToDetails) }
    FeaturedItemsState.Loading ->
        item {
          LoadingIndicator(loaderContentDescription = stringResource(id = R.string.section_feature))
        }
    FeaturedItemsState.Error -> item { FeatureError() }
    else -> {}
  }
}

@Composable
private fun FeatureError() {
  Column {
    HeaderTitle(title = stringResource(id = R.string.section_feature))
    Text(text = "An error has occurred")
  }
}

@Composable
private fun FeaturedItem(items: List<Item>, navigateToDetails: () -> Unit) {
  Column {
    HeaderTitle(title = stringResource(id = R.string.section_feature))

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          items(items) { item ->
            Card(
                modifier =
                    Modifier.size(height = 100.dp, width = 160.dp)
                        .clickable(onClick = { navigateToDetails.invoke() })
                        .semantics { contentDescription = item.description }) {
                  DynamicAsyncImage(
                      modifier = Modifier.fillMaxSize(),
                      imageUrl = item.thumbnailUrl,
                      contentDescription = item.description)
                }
          }
        }
  }
}

private fun LazyListScope.popularSection(
    popularItems: PopularItemsState,
    navigateToDetails: () -> Unit,
    navigateToSeeMore: () -> Unit
) {
  when (popularItems) {
    is PopularItemsState.ShowList ->
        item { PopularItem(popularItems.items, navigateToDetails, navigateToSeeMore) }
    PopularItemsState.Loading ->
        item {
          LoadingIndicator(loaderContentDescription = stringResource(id = R.string.section_popular))
        }
    PopularItemsState.Error -> {
      item { PopularError() }
    }
    else -> {}
  }
}

@Composable
private fun PopularError() {
  Column {
    HeaderTitle(title = stringResource(id = R.string.section_popular))
    Text(text = "An error has occurred")
  }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun PopularItem(
    items: List<Item>,
    navigateToDetails: () -> Unit,
    navigateToSeeMore: () -> Unit
) {
  Column {
    HeaderTitle(title = stringResource(id = R.string.section_popular))

    FlowRow(
        modifier = Modifier.padding(horizontal = 20.dp).fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
          items.forEach { item ->
            Card(
                modifier =
                    Modifier.height(160.dp)
                        .width(100.dp)
                        .clickable(onClick = { navigateToDetails.invoke() })
                        .semantics { contentDescription = item.description }) {
                  DynamicAsyncImage(
                      modifier = Modifier.fillMaxSize(),
                      imageUrl = item.thumbnailUrl,
                      contentDescription = "")
                }
          }

          if (items.size >= 10) { // TODO: check popular size with a Constant?
            Card(
                colors =
                    CardDefaults.cardColors()
                        .copy(containerColor = MaterialTheme.colorScheme.onBackground),
                modifier =
                    Modifier.height(160.dp)
                        .width(100.dp)
                        .clickable(onClick = { navigateToSeeMore.invoke() })) {
                  Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(id = R.string.section_gallery),
                        color = MaterialTheme.colorScheme.background)
                  }
                }
          }
        }
  }
}

@Composable
private fun HeaderTitle(title: String) {
  Text(
      modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp),
      text = title,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleLarge)
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
                popularItemsState = PopularItemsState.ShowList(items),
                featuredItemsState = FeaturedItemsState.ShowList(items)),
        navigateToDetails = {},
        navigateToSeeMore = {})
  }
}
