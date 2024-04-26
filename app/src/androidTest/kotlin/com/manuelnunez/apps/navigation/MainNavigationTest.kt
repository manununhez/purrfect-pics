package com.manuelnunez.apps.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.manuelnunez.apps.MainActivity
import com.manuelnunez.apps.core.ui.component.CustomCardAutomation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.properties.ReadOnlyProperty
import com.manuelnunez.apps.core.ui.R as CoreUIR
import com.manuelnunez.apps.features.detail.ui.R as DetailR
import com.manuelnunez.apps.features.favorites.ui.R as FavorR
import com.manuelnunez.apps.features.home.ui.R as HomeR

@HiltAndroidTest
class MainNavigationTest {
  private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
      ReadOnlyProperty<Any, String> { _, _ -> activity.getString(resId) }

  @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)

  @get:Rule(order = 1) val composeTestRule = createAndroidComposeRule<MainActivity>()

  private val navigateUp by composeTestRule.stringResource(resId = CoreUIR.string.button_back)
  private val home by composeTestRule.stringResource(resId = RootScreen.HOME.contentDescription)
  private val favorite by
      composeTestRule.stringResource(resId = RootScreen.FAVORITES.contentDescription)
  private val details by
      composeTestRule.stringResource(resId = DetailR.string.section_details_title)
  private val featureHomeTitle by composeTestRule.stringResource(HomeR.string.section_feature)
  private val featureFavoriteTitle by
      composeTestRule.stringResource(FavorR.string.section_favorites)
  private val featureDetailFavoriteButton by
      composeTestRule.stringResource(DetailR.string.button_favorite)

  @Before
  fun init() {
    hiltRule.inject()
  }

  @Test
  fun firstTabScreen_startDestination_isHomeNotArrowShown() {
    composeTestRule.apply {
      // Tab Home selected
      onNodeWithContentDescription(home).assertIsSelected()
      onNodeWithContentDescription(favorite).assertIsNotSelected()

      // HomeTitle
      onNodeWithContentDescription(featureHomeTitle).assertExists()

      // GIVEN the user is on any of the top level destinations, THEN the Up arrow is not shown.
      onNodeWithContentDescription(navigateUp).assertDoesNotExist()
    }
  }

  @Test
  fun secondTabScreen_navigateFromHome_isFavoriteNotArrowShown() {
    composeTestRule.apply {
      // Navigate to tab favorites
      onNodeWithContentDescription(favorite).performClick().assertIsSelected()

      // Favorite title
      onNodeWithContentDescription(featureFavoriteTitle).assertExists()

      // Home tab not selected
      onNodeWithContentDescription(home).assertIsNotSelected()

      // GIVEN the user is on any of the top level destinations, THEN the Up arrow is not shown.
      onNodeWithContentDescription(navigateUp).assertDoesNotExist()
    }
  }

  @Test
  fun detailScreen_navigateFromHome_onClickAnyPhoto() {
    composeTestRule.apply {
      // Home tab selected
      onNodeWithContentDescription(home).assertIsSelected()

      // Click on any photo and navigate to details
      onAllNodesWithTag(CustomCardAutomation.IMAGE_CARD_PREFIX).onFirst().performClick()

      // We are in Details Screen
      onNodeWithContentDescription(details).performClick()

      // GIVEN the user is not on any top level destinations, THEN the Up arrow is shown.
      onNodeWithContentDescription(navigateUp).assertExists().performClick()

      // We are in HomeScreen again
      onNodeWithContentDescription(featureHomeTitle).assertExists()
    }
  }

  @Test
  fun detailScreen_navigateFromFavorite_onClickAnyPhoto() {
    composeTestRule.apply {
      // Home tab selected
      onNodeWithContentDescription(home).assertIsSelected()
      addItemToFavorites()

      // Move to tab favorites
      onNodeWithContentDescription(favorite).performClick()
      onNodeWithContentDescription(featureFavoriteTitle).assertExists()

      // Click on any photo and navigate to details
      onAllNodesWithTag(CustomCardAutomation.IMAGE_CARD_PREFIX).onFirst().performClick()

      // We are in Details Screen
      onNodeWithContentDescription(details).performClick()

      // GIVEN the user is not on any top level destinations, THEN the Up arrow is shown.
      onNodeWithContentDescription(navigateUp).assertExists().performClick()

      // We are in HomeScreen again
      onNodeWithContentDescription(featureFavoriteTitle).assertExists()
    }
  }

  @Test
  fun navigationBar_reselectTab_keepsState() {
    composeTestRule.apply {
      // Home tab selected
      onNodeWithContentDescription(home).assertIsSelected()

      // Click on any photo and navigate to details
      onAllNodesWithTag(CustomCardAutomation.IMAGE_CARD_PREFIX).onFirst().performClick()

      // We are in Details Screen
      onNodeWithContentDescription(details).performClick()

      // Move to tab favorites
      onNodeWithContentDescription(favorite).performClick()

      // Move back to home
      onNodeWithContentDescription(home).performClick()

      // We are stills in Details Screen
      onNodeWithContentDescription(details).performClick()
    }
  }

  private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
      .addItemToFavorites() {
    // Click on any photo and navigate to details
    onAllNodesWithTag(CustomCardAutomation.IMAGE_CARD_PREFIX).onFirst().performClick()
    // Add to favorites
    onNodeWithContentDescription(featureDetailFavoriteButton).performClick()
    // Back
    onNodeWithContentDescription(navigateUp).assertExists().performClick()
  }
}
