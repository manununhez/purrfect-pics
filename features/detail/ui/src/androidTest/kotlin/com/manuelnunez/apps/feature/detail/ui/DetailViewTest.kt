package com.manuelnunez.apps.feature.detail.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.feature.detail.ui.components.DetailErrorScreen
import com.manuelnunez.apps.feature.detail.ui.components.DetailScreen
import com.manuelnunez.apps.features.detail.ui.R
import org.junit.Rule
import org.junit.Test

class DetailViewTest {

  @get:Rule(order = 0) val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun photo_whenScreenIsLoaded_showsPhotoShareAndDescription() {
    composeTestRule.setContent { DetailScreen(mockItem, onBackClick = {}) }

    // description
    composeTestRule
        .onNodeWithText(
            mockItem.description,
            substring = true,
        )
        .assertExists()

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
        .onNodeWithContentDescription(
            mockItem.photoId,
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()
  }

  @Test
  fun error_whenError_showsTextAndButtonForGoBack() {
    composeTestRule.setContent { DetailErrorScreen(onBackClick = {}) }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.alert_error_try_again_back),
            substring = true,
        )
        .assertExists()

    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.resources.getString(R.string.button_back),
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()
  }

  private val mockItem =
      Item(
          photoId = "14gf",
          imageUrl = "https://example.com/photo14gf",
          thumbnailUrl = "https://example.com/photo14gf/small",
          description = "This is a description for popular items 14gf")
}
