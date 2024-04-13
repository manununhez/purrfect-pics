package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    cardContentDescription: String,
    elevation: CardElevation = CardDefaults.cardElevation(),
    onClick: (() -> Unit)? = null,
) {
  Card(
      modifier =
          modifier.clickable(onClick = { onClick?.invoke() }).semantics {
            contentDescription = cardContentDescription
          },
      elevation = elevation) {
        DynamicAsyncImage(
            modifier = Modifier.fillMaxSize(), imageUrl = imageUrl, contentDescription = "")
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
