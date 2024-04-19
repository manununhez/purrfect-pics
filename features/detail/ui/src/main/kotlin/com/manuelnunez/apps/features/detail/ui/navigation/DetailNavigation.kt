package com.manuelnunez.apps.features.detail.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.domain.model.toEncodedString
import com.manuelnunez.apps.features.detail.ui.DetailRoute

const val DETAIL_ITEM = "myItem"
const val DETAIL_ROUTE_FAV = "detail_route_fav"
const val DETAIL_ROUTE = "detail_route"

fun NavController.navigateToDetail(item: Item, navOptions: NavOptionsBuilder.() -> Unit = {}) {
  navigate("$DETAIL_ROUTE/${item.toEncodedString()}", navOptions)
}

fun NavGraphBuilder.detailScreen(onBackClick: () -> Unit) {
  composable("$DETAIL_ROUTE/{$DETAIL_ITEM}") { DetailRoute(onBackClick = onBackClick) }
}

fun NavController.navigateToDetailFav(item: Item, navOptions: NavOptionsBuilder.() -> Unit = {}) {
  navigate("$DETAIL_ROUTE_FAV/${item.toEncodedString()}", navOptions)
}

fun NavGraphBuilder.detailFavScreen(onBackClick: () -> Unit) {
  composable("$DETAIL_ROUTE_FAV/{$DETAIL_ITEM}") { DetailRoute(onBackClick = onBackClick) }
}
