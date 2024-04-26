package com.manuelnunez.apps.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.manuelnunez.apps.R
import com.manuelnunez.apps.features.detail.ui.navigation.detailFavScreen
import com.manuelnunez.apps.features.detail.ui.navigation.detailScreen
import com.manuelnunez.apps.features.detail.ui.navigation.navigateToDetail
import com.manuelnunez.apps.features.detail.ui.navigation.navigateToDetailFav
import com.manuelnunez.apps.features.favorites.ui.navigation.FAVORITES_ROUTE
import com.manuelnunez.apps.features.favorites.ui.navigation.favoritesScreen
import com.manuelnunez.apps.features.home.ui.navigation.HOME_ROUTE
import com.manuelnunez.apps.features.home.ui.navigation.homeScreen
import com.manuelnunez.apps.features.seemore.ui.navigation.navigateToSeeMore
import com.manuelnunez.apps.features.seemore.ui.navigation.seeMoreScreen

@Composable
fun MainNavigation(navController: NavHostController) {
  NavHost(navController = navController, startDestination = RootScreen.HOME.route) {
    addHomeRoute(navController)
    addFavoriteRoute(navController)
  }
}

// home navigation
private fun NavGraphBuilder.addHomeRoute(navController: NavController) {
  navigation(route = RootScreen.HOME.route, startDestination = HOME_ROUTE) {
    homeScreen(
        navigateToDetails = navController::navigateToDetail,
        navigateToSeeMore = navController::navigateToSeeMore)
    detailScreen(onBackClick = { navController.navigateUp() })
    seeMoreScreen(
        onBackClick = { navController.navigateUp() },
        navigateToDetails = navController::navigateToDetail)
  }
}

// favorite navigation
private fun NavGraphBuilder.addFavoriteRoute(navController: NavController) {
  navigation(route = RootScreen.FAVORITES.route, startDestination = FAVORITES_ROUTE) {
    favoritesScreen(
        navigateToDetails = navController::navigateToDetailFav,
        onBackClick = { navController.navigateUp() },
    )
    detailFavScreen(onBackClick = { navController.navigateUp() })
  }
}

enum class RootScreen(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
    val contentDescription: Int
) {
  HOME(
      route = "home_root",
      selectedIcon = Icons.Default.Home,
      unselectedIcon = Icons.Outlined.Home,
      iconTextId = R.string.home_destination_title,
      titleTextId = R.string.home_destination_title,
      contentDescription = R.string.home_destination_content_description),
  FAVORITES(
      route = "favorite_root",
      selectedIcon = Icons.Default.Favorite,
      unselectedIcon = Icons.Outlined.FavoriteBorder,
      iconTextId = R.string.favorites_destination_title,
      titleTextId = R.string.favorites_destination_title,
      contentDescription = R.string.favorites_destination_content_description)
}

fun NavController.navigateToRootScreen(rootScreen: RootScreen) {
  navigate(rootScreen.route) {
    launchSingleTop = true
    restoreState = true
    popUpTo(graph.findStartDestination().id) { saveState = true }
  }
}

fun mainDestinations() = listOf(RootScreen.HOME, RootScreen.FAVORITES)
