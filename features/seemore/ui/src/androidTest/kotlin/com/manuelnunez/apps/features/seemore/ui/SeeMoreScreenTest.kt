package com.manuelnunez.apps.features.seemore.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelnunez.apps.core.domain.utils.mockItems
import com.manuelnunez.apps.features.seemore.ui.components.SeeMoreErrorScreen
import com.manuelnunez.apps.features.seemore.ui.components.SeeMoreScreen
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import com.manuelnunez.apps.core.ui.R as RCU

class SeeMoreScreenTest {

  @get:Rule(order = 0) val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun photo_whenScreenIsLoaded_showsListOfPhotos() {
    composeTestRule.setContent {
      SeeMoreScreen(
          items = flowOf(PagingData.from(mockItems)).collectAsLazyPagingItems(),
          onBackClick = {},
          navigateToDetails = {})
    }

    // Title
    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(RCU.string.section_popular),
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
    composeTestRule.setContent { SeeMoreErrorScreen(retry = {}) }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(RCU.string.alert_error_try_again),
            substring = true,
        )
        .assertExists()
  }
}
