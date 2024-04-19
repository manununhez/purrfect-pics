package com.manuelnunez.apps.features.detail.ui.components

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.manuelnunez.apps.core.ui.component.BackToolbar
import com.manuelnunez.apps.core.ui.component.StatefulAsyncImage
import com.manuelnunez.apps.core.ui.component.SurfaceText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.OrientationPreviews
import com.manuelnunez.apps.features.detail.ui.R

@Composable
fun DetailScreen(
    item: Item,
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit,
    onBackClick: () -> Unit
) {
  val orientation = LocalConfiguration.current.orientation

  if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
    DetailLandscape(item, isFavorite, onFavoriteClicked, onBackClick)
  } else {
    DetailPortrait(item, isFavorite, onFavoriteClicked, onBackClick)
  }
}

@Composable
private fun DetailPortrait(
    item: Item,
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit,
    onBackClick: () -> Unit
) {
  Column(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeContent)) {
    Spacer(modifier = Modifier.height(20.dp))

    BackToolbar(
        title = stringResource(id = R.string.section_details_title), onBackClick = onBackClick)

    Spacer(modifier = Modifier.height(10.dp))

    Column(
        modifier = Modifier.weight(1f).wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
          StatefulAsyncImage(
              modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
              imageUrl = item.imageUrl,
              contentDescription = item.photoId,
              contentScale = ContentScale.Fit)

          Row {
            ShareImage(url = item.imageUrl)

            FavoriteButton(onFavoriteClicked, isFavorite)
          }

          SurfaceText(
              modifier = Modifier.padding(top = 10.dp).padding(horizontal = 40.dp),
              textAlign = TextAlign.Center,
              text = item.description,
              style = MaterialTheme.typography.titleSmall)
        }
  }
}

@Composable
private fun FavoriteButton(onFavoriteClicked: () -> Unit, isFavorite: Boolean) {
  IconButton(onClick = onFavoriteClicked) {
    Icon(
        imageVector =
            if (isFavorite) {
              Icons.Filled.Favorite
            } else {
              Icons.Filled.FavoriteBorder
            },
        contentDescription = stringResource(id = R.string.button_favorite),
        tint = MaterialTheme.colorScheme.onSurface)
  }
}

@Composable
private fun DetailLandscape(
    item: Item,
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit,
    onBackClick: () -> Unit
) {
  Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeContent)) {
    Spacer(modifier = Modifier.height(20.dp))

    BackToolbar(
        title = stringResource(id = R.string.section_details_title), onBackClick = onBackClick)

    Spacer(modifier = Modifier.height(10.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
      StatefulAsyncImage(
          modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).weight(0.7f),
          imageUrl = item.imageUrl,
          contentDescription = item.photoId,
          contentScale = ContentScale.Fit)

      Column(Modifier.weight(0.1f)) {
        ShareImage(url = item.imageUrl)

        FavoriteButton(onFavoriteClicked = onFavoriteClicked, isFavorite = isFavorite)
      }

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
    Spacer(modifier = Modifier.height(20.dp))
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
    val item = Item("", "", "", "Description Photo: Selected Photo 4564")
    DetailScreen(
        item = item,
        isFavorite = false,
        onFavoriteClicked = {},
        onBackClick = {},
    )
  }
}
