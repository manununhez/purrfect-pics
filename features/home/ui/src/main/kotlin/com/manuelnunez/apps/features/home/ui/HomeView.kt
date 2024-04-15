package com.manuelnunez.apps.features.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.FeaturedItemsState
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.PopularItemsState
import com.manuelnunez.apps.features.home.ui.components.HomeErrorScreen
import com.manuelnunez.apps.features.home.ui.components.HomeScreen

@Composable
fun HomeView(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToDetails: (Item) -> Unit,
    navigateToSeeMore: () -> Unit
) {
  val items by viewModel.state.collectAsStateWithLifecycle()

  if (items.popularItemsState is PopularItemsState.Error ||
      items.featuredItemsState is FeaturedItemsState.Error) {
    HomeErrorScreen { viewModel.getItems() }
  }

  HomeScreen(items, navigateToDetails, navigateToSeeMore)
}
