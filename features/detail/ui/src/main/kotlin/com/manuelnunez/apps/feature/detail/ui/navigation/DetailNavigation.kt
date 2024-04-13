package com.manuelnunez.apps.feature.detail.ui.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.manuelnunez.apps.core.ui.navigate
import com.manuelnunez.apps.features.home.domain.model.Item

const val DETAIL_ITEM = "myItem"
const val DETAIL_ROUTE = "detail"

fun NavController.navigateToDetail(item: Item, navOptions: NavOptionsBuilder.() -> Unit = {}) {
  val bundle =
      Bundle().apply {
        putParcelable(com.manuelnunez.apps.feature.detail.ui.navigation.DETAIL_ITEM, item)
      }
  navigate(
      route = com.manuelnunez.apps.feature.detail.ui.navigation.DETAIL_ROUTE,
      args = bundle,
      navOptions = navOptions(navOptions))
}

fun NavGraphBuilder.detailScreen(onBackClick: () -> Unit) {
  composable(com.manuelnunez.apps.feature.detail.ui.navigation.DETAIL_ROUTE) { backStackEntry ->
    val myItem =
        backStackEntry.arguments?.getParcelable<Item>(
            com.manuelnunez.apps.feature.detail.ui.navigation.DETAIL_ITEM)
    com.manuelnunez.apps.feature.detail.ui.DetailView(onBackClick = onBackClick, item = myItem)
  }
}