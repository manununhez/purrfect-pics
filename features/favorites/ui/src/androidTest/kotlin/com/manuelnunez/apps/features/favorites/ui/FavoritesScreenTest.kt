package com.manuelnunez.apps.features.favorites.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.favorites.ui.component.FavoritesScreen
import org.junit.Rule
import org.junit.Test

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
}
