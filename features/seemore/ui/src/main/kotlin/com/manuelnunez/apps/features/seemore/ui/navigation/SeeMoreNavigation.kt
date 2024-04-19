package com.manuelnunez.apps.features.seemore.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.seemore.ui.SeeMoreRoute

const val SEE_MORE_ROUTE = "see_more_route"

fun NavController.navigateToSeeMore(navOptions: NavOptionsBuilder.() -> Unit = {}) {
  navigate(SEE_MORE_ROUTE, navOptions)
}

fun NavGraphBuilder.seeMoreScreen(onBackClick: () -> Unit, navigateToDetails: (Item) -> Unit) {
  composable(SEE_MORE_ROUTE) {
    SeeMoreRoute(onBackClick = onBackClick, navigateToDetails = navigateToDetails)
  }
}
