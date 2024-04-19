package com.manuelnunez.apps.features.detail.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.detail.ui.components.DetailScreen
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

  @get:Rule(order = 0) val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun photo_whenScreenIsLoaded_showsPhotoShareAndDescription() {
    composeTestRule.setContent {
      DetailScreen(item = mockItem, isFavorite = false, onBackClick = {}, onFavoriteClicked = {})
    }

    // Detail title
    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.section_details_title),
            substring = true,
        )
        .assertExists()

    // description
    composeTestRule.onNodeWithText(mockItem.description).assertExists()

    // share button
    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.resources.getString(R.string.button_share),
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()

    // Image
    composeTestRule
        .onNodeWithContentDescription(mockItem.photoId)
        .assertExists()
        .assertHasNoClickAction()
  }

  private val mockItem =
      Item(
          photoId = "14gf",
          imageUrl = "https://example.com/photo14gf",
          thumbnailUrl = "https://example.com/photo14gf/small",
          description = "This is a description for popular items 14gf")
}
