package com.manuelnunez.apps

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.manuelnunez.apps.feature.favorites.ui.navigation.navigateToFavorites
import com.manuelnunez.apps.features.home.ui.navigation.navigateToHome

enum class MainDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
  HOME(
      selectedIcon = Icons.Default.Home,
      unselectedIcon = Icons.Outlined.Home,
      iconTextId = R.string.home_destination_title,
      titleTextId = R.string.home_destination_title),
  FAVORITES(
      selectedIcon = Icons.Default.Favorite,
      unselectedIcon = Icons.Outlined.FavoriteBorder,
      iconTextId = R.string.favorites_destination_title,
      titleTextId = R.string.favorites_destination_title)
}

fun NavHostController.onNavigateToDestination(destination: MainDestination) {
  val topLevelNavOptions = navOptions {
    // Pop up to the start destination of the graph to
    // avoid building up a large stack of destinations
    // on the back stack as users select items
    popUpTo(graph.findStartDestination().id) { saveState = true }
    // Avoid multiple copies of the same destination when
    // reselecting the same item
    launchSingleTop = true
    // Restore state when reselecting a previously selected item
    restoreState = true
  }
  when (destination) {
    MainDestination.HOME -> navigateToHome(topLevelNavOptions)
    MainDestination.FAVORITES -> navigateToFavorites(topLevelNavOptions)
  }
}

fun mainDestinations() = listOf(MainDestination.HOME, MainDestination.FAVORITES)
