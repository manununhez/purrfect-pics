package com.manuelnunez.apps.features.favorites.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.ui.FavoritesRoute

const val FAVORITES_ROUTE = "favorites_route"

fun NavGraphBuilder.favoritesScreen(navigateToDetails: (Item) -> Unit, onBackClick: () -> Unit) {
  composable(route = FAVORITES_ROUTE) {
    FavoritesRoute(onFavoriteClicked = navigateToDetails, onBackClick = onBackClick)
  }
}
