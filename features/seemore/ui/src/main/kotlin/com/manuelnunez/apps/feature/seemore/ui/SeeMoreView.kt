package com.manuelnunez.apps.feature.seemore.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.feature.seemore.ui.components.SeeMoreErrorScreen
import com.manuelnunez.apps.feature.seemore.ui.components.SeeMoreScreen

@Composable
fun SeeMoreView(
    viewModel: SeeMoreViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToDetails: (Item) -> Unit
) {

  val testItems =
      List(30) {
        Item(
            "123123$it",
            "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png",
            "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png",
            "Test")
      }

//  if (error) {
//    SeeMoreErrorScreen()
//  } else {
    SeeMoreScreen(
        items = testItems, onBackClick = onBackClick, navigateToDetails = navigateToDetails)
//  }
}
