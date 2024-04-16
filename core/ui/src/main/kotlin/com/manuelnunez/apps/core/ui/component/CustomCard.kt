package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.manuelnunez.apps.core.ui.component.CustomCardAutomation.IMAGE_CARD_PREFIX
import com.manuelnunez.apps.core.ui.component.CustomCardAutomation.TEXT_CARD_PREFIX
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

object CustomCardAutomation {
  const val IMAGE_CARD_PREFIX = "ImageCard"
  const val TEXT_CARD_PREFIX = "TextCard"
}

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    cardContentDescription: String,
    elevation: CardElevation = CardDefaults.cardElevation(),
    contentScale: ContentScale = ContentScale.Crop,
    testTag: String = IMAGE_CARD_PREFIX,
    onClick: (() -> Unit)? = null
) {
  Card(
      modifier =
          modifier
              .clickable(onClick = { onClick?.invoke() })
              .semantics { contentDescription = cardContentDescription }
              .testTag(testTag),
      elevation = elevation) {
        StatefulAsyncImage(
            modifier = Modifier.fillMaxSize(),
            imageUrl = imageUrl,
            contentDescription = "",
            contentScale = contentScale)
      }
}

@Composable
fun TextCard(
    modifier: Modifier = Modifier,
    text: String,
    testTag: String = TEXT_CARD_PREFIX,
    onClick: (() -> Unit)? = null
) {
  Card(
      colors =
          CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.onBackground),
      modifier =
          modifier
              .clickable(onClick = { onClick?.invoke() })
              .semantics { contentDescription = text }
              .testTag(testTag)) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
          Text(
              modifier = Modifier.fillMaxWidth(),
              textAlign = TextAlign.Center,
              text = text,
              color = MaterialTheme.colorScheme.background)
        }
      }
}

@ThemePreviews
@Composable
fun ImageCardPreview() {
  MainTheme {
    ImageCard(
        onClick = {},
        cardContentDescription = "",
        imageUrl = "https://picsum.photos/id/237/200/300")
  }
}

@ThemePreviews
@Composable
fun TextCardPreview() {
  MainTheme { TextCard(onClick = {}, text = "See more") }
}
