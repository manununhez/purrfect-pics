package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cardContentDescription: String,
    imageUrl: String
) {
  Card(
      modifier =
          modifier.clickable(onClick = { onClick.invoke() }).semantics {
            contentDescription = cardContentDescription
          }) {
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
