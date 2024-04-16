package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.ui.R
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
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

/** A simple grid which lays elements out vertically in evenly sized [columns]. */
@Composable
fun VerticalGrid(modifier: Modifier = Modifier, columns: Int = 2, content: @Composable () -> Unit) {
  Layout(content = content, modifier = modifier) { measurables, constraints ->
    val itemWidth = constraints.maxWidth / columns
    // Keep given height constraints, but set an exact width
    val itemConstraints = constraints.copy(minWidth = itemWidth, maxWidth = itemWidth)
    // Measure each item with these constraints
    val placeables = measurables.map { it.measure(itemConstraints) }
    // Track each columns height so we can calculate the overall height
    val columnHeights = Array(columns) { 0 }
    placeables.forEachIndexed { index, placeable ->
      val column = index % columns
      columnHeights[column] += placeable.height
    }
    val height =
        (columnHeights.maxOrNull() ?: constraints.minHeight).coerceAtMost(constraints.maxHeight)
    layout(width = constraints.maxWidth, height = height) {
      // Track the Y co-ord per column we have placed up to
      val columnY = Array(columns) { 0 }
      placeables.forEachIndexed { index, placeable ->
        val column = index % columns
        placeable.placeRelative(x = column * itemWidth, y = columnY[column])
        columnY[column] += placeable.height
      }
    }
  }
}

@ThemePreviews
@Composable
fun AdaptableVerticalGridPreview() {
  MainTheme {
    AdaptableVerticalGrid(decoration = AdaptableVerticalGridDecoration(20.dp, 25.dp, 55.dp)) {
      List(10) {
        Card(Modifier.size(50.dp, 80.dp).padding(horizontal = 10.dp).padding(bottom = 20.dp)) {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.ic_broken_image),
                contentDescription = null)
          }
        }
      }
    }
  }
}
