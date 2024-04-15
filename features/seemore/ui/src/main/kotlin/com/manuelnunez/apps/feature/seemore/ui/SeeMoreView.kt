package com.manuelnunez.apps.feature.seemore.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.feature.seemore.ui.components.SeeMoreErrorScreen
import com.manuelnunez.apps.feature.seemore.ui.components.SeeMoreScreen

@Composable
fun SeeMoreView(
    viewModel: SeeMoreViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToDetails: (Item) -> Unit
) {
  val itemPagingItems = viewModel.itemsPagingDataFlow.collectAsLazyPagingItems()

  SeeMoreScreen(
      items = itemPagingItems, onBackClick = onBackClick, navigateToDetails = navigateToDetails)

  itemPagingItems.apply {
    when {
      loadState.refresh is LoadState.Loading -> {
        // You can add modifier to manage load state when first time response page is loading
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          CircularProgressIndicator()
        }
      }
      loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
        SeeMoreErrorScreen(retry = viewModel::retry)
      }
      else -> {}
    }
  }
}
