package com.manuelnunez.apps

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.manuelnunez.apps.core.ui.component.MainGradientBackground
import com.manuelnunez.apps.core.ui.component.MainNavigationBar
import com.manuelnunez.apps.core.ui.component.MainNavigationBarItem
import com.manuelnunez.apps.navigation.MainNavigation

@Composable
fun MainApp() {
  val navController = rememberNavController()
  var currentMainDestination by rememberSaveable { mutableStateOf(MainDestination.HOME) }

  Scaffold(
      bottomBar = {
        MainNavigationBar {
          mainDestinations().forEach { destination ->
            MainNavigationBarItem(
                selected = currentMainDestination == destination,
                onClick = {
                  currentMainDestination = destination
                  navController.onNavigateToDestination(destination)
                },
                icon = {
                  Icon(
                      imageVector = destination.unselectedIcon,
                      contentDescription = null,
                  )
                },
                selectedIcon = {
                  Icon(
                      imageVector = destination.selectedIcon,
                      contentDescription = null,
                  )
                },
                label = { Text(stringResource(destination.iconTextId)) })
          }
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
          MainGradientBackground { MainNavigation(navController = navController) }
        }
      }
}
