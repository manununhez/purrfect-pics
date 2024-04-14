package com.manuelnunez.apps.feature.seemore.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.feature.seemore.ui.SeeMoreView

const val SEE_MORE_ROUTE = "see_more"

fun NavController.navigateToSeeMore(navOptions: NavOptionsBuilder.() -> Unit = {}) {
  navigate(SEE_MORE_ROUTE, navOptions)
}

fun NavGraphBuilder.seeMoreScreen(onBackClick: () -> Unit, navigateToDetails: (Item) -> Unit) {
  composable(SEE_MORE_ROUTE) { SeeMoreView(onBackClick, navigateToDetails) }
}
