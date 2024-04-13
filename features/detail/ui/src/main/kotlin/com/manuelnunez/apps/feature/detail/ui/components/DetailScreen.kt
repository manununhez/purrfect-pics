package com.manuelnunez.apps.feature.detail.ui.components

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.manuelnunez.apps.core.ui.component.ImageCard
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.OrientationPreviews
import com.manuelnunez.apps.features.detail.ui.R
import com.manuelnunez.apps.features.home.domain.model.Item

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
  Column(modifier = Modifier.fillMaxSize()) {
    DetailToolbar(onBackClick)

    ImageCard(
        modifier = Modifier.weight(1f).padding(top = 20.dp).padding(horizontal = 40.dp),
        cardContentDescription = item.photoId,
        imageUrl = item.imageUrl,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp))

    Column(
        modifier =
            Modifier.fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
          ShareImage(item.imageUrl)
        }

    Column(
        modifier =
            Modifier.fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
              modifier = Modifier.padding(top = 10.dp),
              textAlign = TextAlign.Center,
              text = item.description,
              style = MaterialTheme.typography.titleSmall,
              color = MaterialTheme.colorScheme.onSurface)
        }
  }
}

@Composable
private fun DetailToolbar(onBackClick: () -> Unit) {
  IconButton(onClick = { onBackClick() }) {
    Icon(
        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
        contentDescription = stringResource(id = R.string.button_back),
        tint = MaterialTheme.colorScheme.onSurface)
  }
}

@Composable
private fun DetailLandscape(item: Item, onBackClick: () -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    DetailToolbar(onBackClick)

    Row(verticalAlignment = Alignment.CenterVertically) {
      Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
        ImageCard(
            modifier = Modifier.weight(1f).padding(top = 20.dp).padding(start = 40.dp, end = 20.dp),
            cardContentDescription = item.photoId,
            imageUrl = item.imageUrl,
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp))

        ShareImage(item.imageUrl)
      }

      VerticalDivider()
      Text(
          modifier = Modifier.padding(10.dp).weight(0.3f),
          textAlign = TextAlign.Center,
          text = "Description Photo: Selected Photo $item",
          style = MaterialTheme.typography.titleMedium,
          color = MaterialTheme.colorScheme.onSurface)
    }
  }
}

@Composable
private fun ShareImage(url: String) {
  val context = LocalContext.current
  val textToSend = stringResource(R.string.share_image, url)
  val sendIntent =
      Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, textToSend)
        type = "text/plain"
      }
  val shareIntent = Intent.createChooser(sendIntent, "Share Image")

  IconButton(onClick = { ContextCompat.startActivity(context, shareIntent, null) }) {
    Icon(
        imageVector = Icons.Filled.Share,
        contentDescription = stringResource(id = R.string.button_share),
        tint = MaterialTheme.colorScheme.onSurface)
  }
}

@OrientationPreviews
@Composable
fun DetailScreenPreview() {
  MainTheme { DetailScreen(onBackClick = {}, item = Item("", "", "", "")) }
}
