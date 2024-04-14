package com.manuelnunez.apps.feature.detail.ui.components

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.ui.component.DynamicAsyncImage
import com.manuelnunez.apps.core.ui.component.TitleText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.OrientationPreviews
import com.manuelnunez.apps.features.detail.ui.R

@Composable
fun DetailScreen(item: Item, onBackClick: () -> Unit) {
  val orientation = LocalConfiguration.current.orientation
  if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
    DetailLandscape(item, onBackClick)
  } else {
    DetailPortrait(item, onBackClick)
  }
}

@Composable
private fun DetailPortrait(item: Item, onBackClick: () -> Unit) {
  Column {
    Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))

    DetailToolbar(onBackClick)

    Column(
        modifier = Modifier.weight(1f).wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
          DynamicAsyncImage(
              modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
              imageUrl = item.imageUrl,
              contentDescription = item.photoId,
              contentScale = ContentScale.Fit)

          ShareImage(url = item.imageUrl)

          Text(
              modifier = Modifier.padding(top = 10.dp).padding(horizontal = 40.dp),
              textAlign = TextAlign.Center,
              text = item.description,
              style = MaterialTheme.typography.titleSmall,
              color = MaterialTheme.colorScheme.onSurface)

          Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }

    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
  }
}

@Composable
private fun DetailToolbar(onBackClick: () -> Unit) {
  Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    IconButton(onClick = { onBackClick() }) {
      Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
          contentDescription = stringResource(id = R.string.button_back),
          tint = MaterialTheme.colorScheme.onSurface)
    }

    TitleText(title = "Details")
  }
}

@Composable
private fun DetailLandscape(item: Item, onBackClick: () -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))

    DetailToolbar(onBackClick)

    Row(verticalAlignment = Alignment.CenterVertically) {
      DynamicAsyncImage(
          modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).weight(0.7f),
          imageUrl = item.imageUrl,
          contentDescription = item.photoId,
          contentScale = ContentScale.Fit)

      ShareImage(modifier = Modifier.weight(0.1f), url = item.imageUrl)

      VerticalDivider()

      Text(
          modifier = Modifier.padding(10.dp).weight(0.2f),
          textAlign = TextAlign.Center,
          text = item.description,
          maxLines = 10,
          overflow = TextOverflow.Ellipsis,
          style = MaterialTheme.typography.titleMedium,
          color = MaterialTheme.colorScheme.onSurface)
    }
    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
  }
}

@Composable
private fun ShareImage(modifier: Modifier = Modifier, url: String) {
  val context = LocalContext.current
  val textToSend = stringResource(R.string.share_image_message, url)
  val title = stringResource(R.string.share_image_title, url)
  val sendIntent =
      Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, textToSend)
        type = "text/plain"
      }
  val shareIntent = Intent.createChooser(sendIntent, title)

  IconButton(
      modifier = modifier, onClick = { ContextCompat.startActivity(context, shareIntent, null) }) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = stringResource(id = R.string.button_share),
            tint = MaterialTheme.colorScheme.onSurface)
      }
}

@OrientationPreviews
@Composable
fun DetailScreenPreview() {
  MainTheme {
    DetailScreen(
        onBackClick = {}, item = Item("", "", "", "Description Photo: Selected Photo 4564"))
  }
}
