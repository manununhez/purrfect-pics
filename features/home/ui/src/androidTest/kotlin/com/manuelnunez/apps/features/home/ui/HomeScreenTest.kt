package com.manuelnunez.apps.features.home.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.features.home.ui.HomeViewModel.FeaturedItemsState
import com.manuelnunez.apps.features.home.ui.HomeViewModel.HomeUiState
import com.manuelnunez.apps.features.home.ui.HomeViewModel.PopularItemsState
import com.manuelnunez.apps.features.home.ui.components.HomeErrorScreen
import com.manuelnunez.apps.features.home.ui.components.HomeScreen
import org.junit.Rule
import org.junit.Test
import com.manuelnunez.apps.core.ui.R as RCU

class HomeScreenTest {

  @get:Rule(order = 0) val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun circularProgressIndicator_whenScreenIsLoading_exists_forFeatureAndPopularSections() {
    composeTestRule.setContent {
      HomeScreen(
          items =
              HomeUiState(
                  popularItemsState = PopularItemsState.Loading,
                  featuredItemsState = FeaturedItemsState.Loading),
          navigateToDetails = {},
          navigateToSeeMore = {})
    }
    // Feature loader
    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.resources.getString(R.string.section_feature),
        )
        .assertExists()

    // Popular loader
    composeTestRule
        .onNodeWithContentDescription(
            composeTestRule.activity.resources.getString(RCU.string.section_popular),
        )
        .assertExists()
  }

  @Test
  fun photos_whenScreenIsLoaded_showsFeatureAndPopularPhotos() {
    composeTestRule.setContent {
      HomeScreen(
          items =
              HomeUiState(
                  popularItemsState = PopularItemsState.ShowList(mockPopularPhotos),
                  featuredItemsState = FeaturedItemsState.ShowList(mockFeaturedPhotos)),
          navigateToDetails = {},
          navigateToSeeMore = {})
    }

    // Feature title
    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.section_feature),
            substring = true,
        )
        .assertExists()

    composeTestRule
        .onNodeWithContentDescription(
            mockFeaturedPhotos[0].description,
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()

    // Popular title
    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(RCU.string.section_popular),
            substring = true,
        )
        .assertExists()

    composeTestRule
        .onNodeWithContentDescription(
            mockPopularPhotos[0].description,
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()
  }

  @Test
  fun error_whenError_showsTextAndButtonForRetry() {
    composeTestRule.setContent { HomeErrorScreen(retry = {}) }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(RCU.string.alert_error_try_again),
            substring = true,
        )
        .assertExists()
  }

  @Test
  fun error_whenError_showsAlertWhenFeatureFailed() {
    composeTestRule.setContent {
      HomeScreen(
          items =
              HomeUiState(
                  popularItemsState = PopularItemsState.ShowList(mockPopularPhotos),
                  featuredItemsState = FeaturedItemsState.Error),
          navigateToDetails = {},
          navigateToSeeMore = {})
    }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.alert_error_feature),
            substring = true,
        )
        .assertExists()
  }

  @Test
  fun error_whenError_showsAlertWhenPopularFailed() {
    composeTestRule.setContent {
      HomeScreen(
          items =
              HomeUiState(
                  popularItemsState = PopularItemsState.Error,
                  featuredItemsState = FeaturedItemsState.ShowList(mockFeaturedPhotos)),
          navigateToDetails = {},
          navigateToSeeMore = {})
    }

    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.alert_error_popular),
            substring = true,
        )
        .assertExists()
  }

  private val mockPopularPhotos =
      List(20) { index ->
            val id = (index + 1).toString()
            Item(
                photoId = id,
                imageUrl = "https://example.com/photo$id",
                thumbnailUrl = "https://example.com/photo$id/small",
                description = "This is a description for popular items $id")
          }
          .shuffled()
          .take(10)

  private val mockFeaturedPhotos =
      List(20) { index ->
            val id = (index + 1).toString()
            Item(
                photoId = id,
                imageUrl = "https://example.com/photo$id",
                thumbnailUrl = "https://example.com/photo$id/small",
                description = "This is a description for featured items $id")
          }
          .shuffled()
          .take(5)
}
