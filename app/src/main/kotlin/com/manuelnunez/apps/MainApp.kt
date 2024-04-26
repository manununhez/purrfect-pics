package com.manuelnunez.apps

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.manuelnunez.apps.core.ui.component.MainGradientBackground
import com.manuelnunez.apps.core.ui.component.MainNavigationBar
import com.manuelnunez.apps.core.ui.component.MainNavigationBarItem
import com.manuelnunez.apps.core.ui.component.MainNavigationRail
import com.manuelnunez.apps.core.ui.component.MainNavigationRailItem
import com.manuelnunez.apps.navigation.MainNavigation
import com.manuelnunez.apps.navigation.RootScreen
import com.manuelnunez.apps.navigation.mainDestinations
import com.manuelnunez.apps.navigation.navigateToRootScreen

@Composable
fun MainApp() {
  val navController = rememberNavController()
  val currentSelectedScreen by navController.currentScreenAsState()
  val shouldShowBottomBar =
      LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

  Scaffold(
      bottomBar = {
        if (shouldShowBottomBar) {
          MainBottomNavBar(navController, currentSelectedScreen)
        }
      }) { paddingValues ->
        Row(
            Modifier.fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
          if (!shouldShowBottomBar) {
            MainNavRail(navController, currentSelectedScreen)
          }
          MainGradientBackground { MainNavigation(navController) }
        }
      }
}

@Composable
private fun MainBottomNavBar(navController: NavController, currentSelectedScreen: RootScreen) {
  MainNavigationBar {
    mainDestinations().forEach { rootScreen ->
      val contentDescriptionScreen = stringResource(id = rootScreen.contentDescription)

      MainNavigationBarItem(
          modifier = Modifier.semantics { contentDescription = contentDescriptionScreen },
          selected = currentSelectedScreen == rootScreen,
          onClick = { navController.navigateToRootScreen(rootScreen) },
          icon = {
            Icon(
                imageVector = rootScreen.unselectedIcon,
                contentDescription = null,
            )
          },
          selectedIcon = {
            Icon(
                imageVector = rootScreen.selectedIcon,
                contentDescription = null,
            )
          },
          label = { Text(stringResource(rootScreen.iconTextId)) })
    }
  }
}

@Composable
private fun MainNavRail(navController: NavController, currentSelectedScreen: RootScreen) {
  MainNavigationRail {
    mainDestinations().forEach { rootScreen ->
      val contentDescriptionScreen = stringResource(id = rootScreen.contentDescription)
      MainNavigationRailItem(
          modifier = Modifier.semantics { contentDescription = contentDescriptionScreen },
          selected = currentSelectedScreen == rootScreen,
          onClick = { navController.navigateToRootScreen(rootScreen) },
          icon = {
            Icon(
                imageVector = rootScreen.unselectedIcon,
                contentDescription = null,
            )
          },
          selectedIcon = {
            Icon(
                imageVector = rootScreen.selectedIcon,
                contentDescription = null,
            )
          },
          label = { Text(stringResource(rootScreen.iconTextId)) })
    }
  }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RootScreen> {
  val selectedItem = remember { mutableStateOf(RootScreen.HOME) }
  DisposableEffect(key1 = this) {
    val listener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
          when {
            destination.hierarchy.any { it.route == RootScreen.HOME.route } -> {
              selectedItem.value = RootScreen.HOME
            }
            destination.hierarchy.any { it.route == RootScreen.FAVORITES.route } -> {
              selectedItem.value = RootScreen.FAVORITES
            }
          }
        }

    addOnDestinationChangedListener(listener)

    onDispose { removeOnDestinationChangedListener(listener) }
  }
  return selectedItem
}
