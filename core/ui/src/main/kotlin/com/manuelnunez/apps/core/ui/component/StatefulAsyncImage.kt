package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.rememberAsyncImagePainter
import com.manuelnunez.apps.core.ui.R

@Composable
fun StatefulAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
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

    Image(
        modifier = modifier,
        painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
        contentScale =
            if (isError.not() && !isLocalInspection) contentScale else ContentScale.FillBounds,
        contentDescription = contentDescription)
  }
}