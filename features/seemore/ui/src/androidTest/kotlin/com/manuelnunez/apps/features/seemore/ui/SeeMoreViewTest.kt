package com.manuelnunez.apps.features.seemore.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.seemore.ui.components.SeeMoreErrorScreen
import com.manuelnunez.apps.features.seemore.ui.components.SeeMoreScreen
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import com.manuelnunez.apps.core.ui.R as RCU

class SeeMoreViewTest {

  @get:Rule(order = 0) val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun photo_whenScreenIsLoaded_showsPhotoShareAndDescription() {
    composeTestRule.setContent {
      SeeMoreScreen(
          items = flowOf(PagingData.from(mockItems)).collectAsLazyPagingItems(),
          onBackClick = {},
          navigateToDetails = {})
    }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(RCU.string.section_popular),
            substring = true,
        )
        .assertExists()

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
    composeTestRule.setContent { SeeMoreErrorScreen(retry = {}) }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(RCU.string.alert_error_try_again),
            substring = true,
        )
        .assertExists()
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
