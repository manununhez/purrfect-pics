package com.manuelnunez.apps.core.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun SurfaceText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    style: TextStyle = MaterialTheme.typography.titleLarge
) {
  Text(
      modifier = modifier.semantics { contentDescription = text },
      text = text,
      textAlign = textAlign,
      color = MaterialTheme.colorScheme.onSurface,
      style = style)
}

@ThemePreviews
@Composable
fun TitleTextPreview() {
  MainTheme { SurfaceText(text = "See more") }
}
