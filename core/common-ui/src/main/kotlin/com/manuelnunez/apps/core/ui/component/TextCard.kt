package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun TextCard(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
  Card(
      colors =
          CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.onBackground),
      modifier = modifier.clickable(onClick = onClick).semantics { contentDescription = text }) {
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
fun TextCardPreview() {
  MainTheme { TextCard(onClick = {}, text = "See more") }
}
