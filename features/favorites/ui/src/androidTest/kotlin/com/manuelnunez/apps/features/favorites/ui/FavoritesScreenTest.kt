package com.manuelnunez.apps.features.favorites.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.favorites.ui.component.FavoritesErrorScreen
import com.manuelnunez.apps.features.favorites.ui.component.FavoritesScreen
import org.junit.Rule
import org.junit.Test
import com.manuelnunez.apps.core.ui.R as RCU

class FavoritesScreenTest {
  @get:Rule(order = 0) val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun photo_whenScreenIsLoaded_showsListOfPhotos() {
    composeTestRule.setContent {
      FavoritesScreen(items = mockItems, navigateToDetails = {}, onBackClick = {})
    }

    // Title
    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.section_favorites),
            substring = true,
        )
        .assertExists()

    // Content
    composeTestRule
        .onNodeWithContentDescription(
            mockItems[0].description,
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()
  }

  @Test
  fun error_whenError_showsTextAndButtonForGoBack() {
    composeTestRule.setContent { FavoritesErrorScreen(onBackClick = {}) }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(RCU.string.alert_error_try_again_back),
            substring = true,
        )
        .assertExists()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.resources.getString(RCU.string.alert_dialog_confirm_button),
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()
  }

  private val mockItems =
      List(5) { index ->
        Item(
            "$index",
            "https://example.com/$index",
            description = "description: $index",
            thumbnailUrl = "https://example.com/$index")
      }
}
