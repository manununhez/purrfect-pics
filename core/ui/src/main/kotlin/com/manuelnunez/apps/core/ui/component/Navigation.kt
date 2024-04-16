package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

/** Wraps Material 3 [NavigationBarItem]. */
@Composable
fun RowScope.MainNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
  NavigationBarItem(
      selected = selected,
      onClick = onClick,
      icon = if (selected) selectedIcon else icon,
      modifier = modifier,
      enabled = enabled,
      label = label,
      alwaysShowLabel = alwaysShowLabel,
      colors =
          NavigationBarItemDefaults.colors(
              selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
              unselectedIconColor = NavigationDefaults.navigationContentColor(),
              selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
              unselectedTextColor = NavigationDefaults.navigationContentColor(),
              indicatorColor = NavigationDefaults.navigationIndicatorColor(),
          ),
  )
}

/** Wraps Material 3 [NavigationBar]. */
@Composable
fun MainNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
  NavigationBar(
      modifier = modifier,
      contentColor = NavigationDefaults.navigationContentColor(),
      tonalElevation = 0.dp,
      content = content,
  )
}

/** Wraps Material 3 [NavigationRailItem]. */
@Composable
fun MainNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
  NavigationRailItem(
      selected = selected,
      onClick = onClick,
      icon = if (selected) selectedIcon else icon,
      modifier = modifier,
      enabled = enabled,
      label = label,
      alwaysShowLabel = alwaysShowLabel,
      colors =
          NavigationRailItemDefaults.colors(
              selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
              unselectedIconColor = NavigationDefaults.navigationContentColor(),
              selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
              unselectedTextColor = NavigationDefaults.navigationContentColor(),
              indicatorColor = NavigationDefaults.navigationIndicatorColor(),
          ),
  )
}

/** Wraps Material 3 [NavigationRail]. */
@Composable
fun MainNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
  NavigationRail(
      modifier = modifier,
      containerColor = Color.Transparent,
      contentColor = NavigationDefaults.navigationContentColor(),
      header = header,
      content = content,
  )
}

@ThemePreviews
@Composable
fun NavigationBarPreview() {
  val items = listOf("Home", "Favorites")
  val icons = listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder)
  val selectedIcons = listOf(Icons.Default.Home, Icons.Default.Favorite)

  MainTheme {
    MainNavigationBar {
      items.forEachIndexed { index, item ->
        MainNavigationBarItem(
            icon = {
              Icon(
                  imageVector = icons[index],
                  contentDescription = item,
              )
            },
            selectedIcon = {
              Icon(
                  imageVector = selectedIcons[index],
                  contentDescription = item,
              )
            },
            label = { Text(item) },
            selected = index == 0,
            onClick = {},
        )
      }
    }
  }
}

@ThemePreviews
@Composable
fun NavigationRailPreview() {
  val items = listOf("Home", "Favorites")
  val icons = listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder)
  val selectedIcons = listOf(Icons.Default.Home, Icons.Default.Favorite)

  MainTheme {
    MainNavigationRail {
      items.forEachIndexed { index, item ->
        MainNavigationRailItem(
            icon = {
              Icon(
                  imageVector = icons[index],
                  contentDescription = item,
              )
            },
            selectedIcon = {
              Icon(
                  imageVector = selectedIcons[index],
                  contentDescription = item,
              )
            },
            label = { Text(item) },
            selected = index == 1,
            onClick = {},
        )
      }
    }
  }
}

object NavigationDefaults {
  @Composable fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

  @Composable fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

  @Composable fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
