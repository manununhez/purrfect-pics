package com.manuelnunez.apps.features.home.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.home.ui.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavGraphBuilder.homeScreen(navigateToDetails: (Item) -> Unit, navigateToSeeMore: () -> Unit) {
  composable(route = HOME_ROUTE) {
    HomeRoute(navigateToDetails = navigateToDetails, navigateToSeeMore = navigateToSeeMore)
  }
}
