package com.manuelnunez.apps.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.manuelnunez.apps.feature.favorites.ui.navigation.favoritesScreen
import com.manuelnunez.apps.features.detail.ui.navigation.detailScreen
import com.manuelnunez.apps.features.detail.ui.navigation.navigateToDetail
import com.manuelnunez.apps.features.home.ui.navigation.HOME_ROUTE
import com.manuelnunez.apps.features.home.ui.navigation.homeScreen
import com.manuelnunez.apps.features.seemore.ui.navigation.navigateToSeeMore
import com.manuelnunez.apps.features.seemore.ui.navigation.seeMoreScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = HOME_ROUTE,
) {
  NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
    homeScreen(
        navigateToDetails = navController::navigateToDetail,
        navigateToSeeMore = navController::navigateToSeeMore)
    detailScreen(onBackClick = { navController.navigateUp() })
    seeMoreScreen(
        onBackClick = { navController.navigateUp() },
        navigateToDetails = navController::navigateToDetail)
    favoritesScreen()
  }
}
