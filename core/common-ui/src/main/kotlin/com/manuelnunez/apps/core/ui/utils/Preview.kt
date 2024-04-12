package com.manuelnunez.apps.core.ui.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * Multipreview annotation that represents various device sizes. Add this annotation to a composable
 * to render various devices.
 */
@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
annotation class DevicePreviews

/**
 * Multipreview annotation that represents various font sizes. Add this annotation to a composable
 * to render various font sizes.
 */
@Preview(
    name = "Small font",
    group = "font scales",
    fontScale = 0.5f,
    showBackground = true,
)
@Preview(
    name = "Large font",
    group = "font scales",
    fontScale = 2f,
    showBackground = true,
)
@Preview(
    name = "Normal font",
    group = "font scales",
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
annotation class FontScalingPreviews

/**
 * Multipreview annotation that represents light and dark themes. Add this annotation to a
 * composable to render the both themes.
 */
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, group = "Themes", name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, group = "Themes", name = "Dark theme")
annotation class ThemePreviews
