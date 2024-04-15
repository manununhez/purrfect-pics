package com.manuelnunez.apps.features.home.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.home.ui.HomeView

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(navigateToDetails: (Item) -> Unit, navigateToSeeMore: () -> Unit) {
  composable(route = HOME_ROUTE) {
    HomeView(navigateToDetails = navigateToDetails, navigateToSeeMore = navigateToSeeMore)
  }
}
