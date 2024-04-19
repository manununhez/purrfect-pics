package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.rememberAsyncImagePainter
import com.manuelnunez.apps.core.ui.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun StatefulAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    zoomable: Boolean = true,
    placeholder: Painter = painterResource(R.drawable.ic_broken_image),
    contentScale: ContentScale = ContentScale.Crop
) {
  var isLoading by remember { mutableStateOf(true) }
  var isError by remember { mutableStateOf(false) }
  val imageLoader =
      rememberAsyncImagePainter(
          model = imageUrl,
          onState = { state ->
            isLoading = state is Loading
            isError = state is Error
          })
  val isLocalInspection = LocalInspectionMode.current

  Box(
      modifier = modifier,
      contentAlignment = Alignment.Center,
  ) {
    if (isLoading && !isLocalInspection) {
      // Display a progress bar while loading
      CircularProgressIndicator(
          modifier = Modifier.align(Alignment.Center).size(80.dp),
          color = MaterialTheme.colorScheme.tertiary,
      )
    }

    if (zoomable) {
      ZoomableImage(
          modifier,
          isError,
          isLocalInspection,
          imageLoader,
          placeholder,
          contentScale,
          contentDescription)
    } else {
      Image(
          modifier = modifier,
          painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
          contentScale =
              if (isError.not() && !isLocalInspection) contentScale else ContentScale.FillBounds,
          contentDescription = contentDescription)
    }
  }
}

@Composable
private fun ZoomableImage(
    modifier: Modifier,
    isError: Boolean,
    isLocalInspection: Boolean,
    imageLoader: AsyncImagePainter,
    placeholder: Painter,
    contentScale: ContentScale,
    contentDescription: String
) {
  val angle by remember { mutableFloatStateOf(0f) }
  var zoom by remember { mutableFloatStateOf(1f) }
  var offsetX by remember { mutableFloatStateOf(0f) }
  var offsetY by remember { mutableFloatStateOf(0f) }

  val configuration = LocalConfiguration.current
  val screenWidth = configuration.screenWidthDp.dp.value
  val screenHeight = configuration.screenHeightDp.dp.value

  Image(
      modifier =
          modifier
              .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
              .graphicsLayer(scaleX = zoom, scaleY = zoom, rotationZ = angle)
              .pointerInput(Unit) {
                detectTransformGestures(
                    onGesture = { _, pan, gestureZoom, _ ->
                      zoom = (zoom * gestureZoom).coerceIn(1F..4F)
                      if (zoom > 1) {
                        val x = (pan.x * zoom)
                        val y = (pan.y * zoom)
                        val angleRad = angle * PI / 180.0

                        offsetX =
                            (offsetX + (x * cos(angleRad) - y * sin(angleRad)).toFloat()).coerceIn(
                                -(screenWidth * zoom)..(screenWidth * zoom))
                        offsetY =
                            (offsetY + (x * sin(angleRad) + y * cos(angleRad)).toFloat()).coerceIn(
                                -(screenHeight * zoom)..(screenHeight * zoom))
                      } else {
                        offsetX = 0F
                        offsetY = 0F
                      }
                    })
              },
      painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
      contentScale =
          if (isError.not() && !isLocalInspection) contentScale else ContentScale.FillBounds,
      contentDescription = contentDescription)
}
