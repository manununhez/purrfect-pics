package com.manuelnunez.apps.feature.favorites.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.manuelnunez.apps.feature.favorites.ui.FavoritesView

const val FAVORITES_ROUTE = "favorites_route"

fun NavController.navigateToFavorites(navOptions: NavOptions) =
    navigate(FAVORITES_ROUTE, navOptions)

fun NavGraphBuilder.favoritesScreen() {
  composable(route = FAVORITES_ROUTE) { FavoritesView() }
}
