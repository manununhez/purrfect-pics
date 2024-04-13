package com.manuelnunez.apps.core.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun AdaptableVerticalGrid(
    modifier: Modifier = Modifier,
    decoration: AdaptableVerticalGridDecoration,
    content: @Composable () -> Unit
) {
  val config = LocalConfiguration.current

  val screenWidth =
      config.screenWidthDp.dp - (decoration.gridPadding - decoration.itemHorizontalMargin) * 2
  val columns =
      (screenWidth / (decoration.itemWidth + decoration.itemHorizontalMargin * 2)).roundToInt()

  VerticalGrid(modifier, columns, content)
}

data class AdaptableVerticalGridDecoration(
    val gridPadding: Dp,
    val itemWidth: Dp,
    val itemHorizontalMargin: Dp,
)
