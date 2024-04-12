package com.manuelnunez.apps.features.home.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.manuelnunez.apps.features.home.ui.components.HomeScreen
import com.manuelnunez.apps.features.home.ui.utils.mockFeaturedPhotos
import com.manuelnunez.apps.features.home.ui.utils.mockPopularPhotos
import org.junit.Rule
import org.junit.Test

class HomeViewTest {

  @get:Rule(order = 0) val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun circularProgressIndicator_whenScreenIsLoading_exists_forFeatureAndPopularSections() {
    composeTestRule.setContent {
      HomeScreen(
          items =
              HomeScreenViewModel.HomeUiState(
                  popularItemsState = HomeScreenViewModel.PopularItemsState.Loading,
                  featuredItemsState = HomeScreenViewModel.FeaturedItemsState.Loading),
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
            composeTestRule.activity.resources.getString(R.string.section_popular),
        )
        .assertExists()
  }

  @Test
  fun photos_whenScreenIsLoaded_showsFeatureAndPopularPhotos() {
    composeTestRule.setContent {
      HomeScreen(
          items =
              HomeScreenViewModel.HomeUiState(
                  popularItemsState =
                      HomeScreenViewModel.PopularItemsState.ShowList(mockPopularPhotos),
                  featuredItemsState =
                      HomeScreenViewModel.FeaturedItemsState.ShowList(mockFeaturedPhotos)),
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
            mockPopularPhotos[0].description,
            substring = true,
        )
        .assertExists()
        .assertHasClickAction()

    // Popular title
    composeTestRule
        .onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.section_popular),
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
  }
}
