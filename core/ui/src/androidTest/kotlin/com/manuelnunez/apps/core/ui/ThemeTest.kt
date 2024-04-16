package com.manuelnunez.apps.core.ui

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.ui.theme.BackgroundTheme
import com.manuelnunez.apps.core.ui.theme.DarkColorScheme
import com.manuelnunez.apps.core.ui.theme.GradientColors
import com.manuelnunez.apps.core.ui.theme.LightColorScheme
import com.manuelnunez.apps.core.ui.theme.LocalBackgroundTheme
import com.manuelnunez.apps.core.ui.theme.LocalGradientColors
import com.manuelnunez.apps.core.ui.theme.MainTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ThemeTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun darkThemeFalse_dynamicColorFalse_androidThemeFalse() {
    composeTestRule.setContent {
      MainTheme(
          darkTheme = false,
          disableDynamicTheming = true,
      ) {
        val colorScheme = LightColorScheme
        assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
        val gradientColors = defaultGradientColors(colorScheme)
        assertEquals(gradientColors, LocalGradientColors.current)
        val backgroundTheme = defaultBackgroundTheme(colorScheme)
        assertEquals(backgroundTheme, LocalBackgroundTheme.current)
      }
    }
  }

  @Test
  fun darkThemeTrue_dynamicColorFalse_androidThemeFalse() {
    composeTestRule.setContent {
      MainTheme(
          darkTheme = true,
          disableDynamicTheming = true,
      ) {
        val colorScheme = DarkColorScheme
        assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
        val gradientColors = defaultGradientColors(colorScheme)
        assertEquals(gradientColors, LocalGradientColors.current)
        val backgroundTheme = defaultBackgroundTheme(colorScheme)
        assertEquals(backgroundTheme, LocalBackgroundTheme.current)
      }
    }
  }

  @Test
  fun darkThemeFalse_dynamicColorTrue_androidThemeFalse() {
    composeTestRule.setContent {
      MainTheme(
          darkTheme = false,
          disableDynamicTheming = false,
      ) {
        val colorScheme = dynamicLightColorSchemeWithFallback()
        assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
        val gradientColors = dynamicGradientColorsWithFallback(colorScheme)
        assertEquals(gradientColors, LocalGradientColors.current)
        val backgroundTheme = defaultBackgroundTheme(colorScheme)
        assertEquals(backgroundTheme, LocalBackgroundTheme.current)
      }
    }
  }

  @Test
  fun darkThemeTrue_dynamicColorTrue_androidThemeFalse() {
    composeTestRule.setContent {
      MainTheme(
          darkTheme = true,
          disableDynamicTheming = false,
      ) {
        val colorScheme = dynamicDarkColorSchemeWithFallback()
        assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
        val gradientColors = dynamicGradientColorsWithFallback(colorScheme)
        assertEquals(gradientColors, LocalGradientColors.current)
        val backgroundTheme = defaultBackgroundTheme(colorScheme)
        assertEquals(backgroundTheme, LocalBackgroundTheme.current)
      }
    }
  }

  @Composable
  private fun dynamicLightColorSchemeWithFallback(): ColorScheme =
      when {
        SDK_INT >= VERSION_CODES.S -> dynamicLightColorScheme(LocalContext.current)
        else -> LightColorScheme
      }

  @Composable
  private fun dynamicDarkColorSchemeWithFallback(): ColorScheme =
      when {
        SDK_INT >= VERSION_CODES.S -> dynamicDarkColorScheme(LocalContext.current)
        else -> DarkColorScheme
      }

  private fun emptyGradientColors(colorScheme: ColorScheme): GradientColors =
      GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))

  private fun defaultGradientColors(colorScheme: ColorScheme): GradientColors =
      GradientColors(
          top = colorScheme.inverseOnSurface,
          bottom = colorScheme.primaryContainer,
          container = colorScheme.surface,
      )

  private fun dynamicGradientColorsWithFallback(colorScheme: ColorScheme): GradientColors =
      when {
        SDK_INT >= VERSION_CODES.S -> emptyGradientColors(colorScheme)
        else -> defaultGradientColors(colorScheme)
      }

  private fun defaultBackgroundTheme(colorScheme: ColorScheme): BackgroundTheme =
      BackgroundTheme(
          color = colorScheme.surface,
          tonalElevation = 2.dp,
      )

  /** Workaround for the fact that the NiA design system specify all color scheme values. */
  private fun assertColorSchemesEqual(
      expectedColorScheme: ColorScheme,
      actualColorScheme: ColorScheme,
  ) {
    assertEquals(expectedColorScheme.primary, actualColorScheme.primary)
    assertEquals(expectedColorScheme.onPrimary, actualColorScheme.onPrimary)
    assertEquals(expectedColorScheme.primaryContainer, actualColorScheme.primaryContainer)
    assertEquals(expectedColorScheme.onPrimaryContainer, actualColorScheme.onPrimaryContainer)
    assertEquals(expectedColorScheme.secondary, actualColorScheme.secondary)
    assertEquals(expectedColorScheme.onSecondary, actualColorScheme.onSecondary)
    assertEquals(expectedColorScheme.secondaryContainer, actualColorScheme.secondaryContainer)
    assertEquals(
        expectedColorScheme.onSecondaryContainer,
        actualColorScheme.onSecondaryContainer,
    )
    assertEquals(expectedColorScheme.tertiary, actualColorScheme.tertiary)
    assertEquals(expectedColorScheme.onTertiary, actualColorScheme.onTertiary)
    assertEquals(expectedColorScheme.tertiaryContainer, actualColorScheme.tertiaryContainer)
    assertEquals(expectedColorScheme.onTertiaryContainer, actualColorScheme.onTertiaryContainer)
    assertEquals(expectedColorScheme.error, actualColorScheme.error)
    assertEquals(expectedColorScheme.onError, actualColorScheme.onError)
    assertEquals(expectedColorScheme.errorContainer, actualColorScheme.errorContainer)
    assertEquals(expectedColorScheme.onErrorContainer, actualColorScheme.onErrorContainer)
    assertEquals(expectedColorScheme.background, actualColorScheme.background)
    assertEquals(expectedColorScheme.onBackground, actualColorScheme.onBackground)
    assertEquals(expectedColorScheme.surface, actualColorScheme.surface)
    assertEquals(expectedColorScheme.onSurface, actualColorScheme.onSurface)
    assertEquals(expectedColorScheme.surfaceVariant, actualColorScheme.surfaceVariant)
    assertEquals(expectedColorScheme.onSurfaceVariant, actualColorScheme.onSurfaceVariant)
    assertEquals(expectedColorScheme.inverseSurface, actualColorScheme.inverseSurface)
    assertEquals(expectedColorScheme.inverseOnSurface, actualColorScheme.inverseOnSurface)
    assertEquals(expectedColorScheme.outline, actualColorScheme.outline)
  }
}
