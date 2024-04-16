package com.manuelnunez.apps.core.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String, textAlign: TextAlign? = null) {
  Text(
      modifier = modifier,
      text = title,
      textAlign = textAlign,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleLarge)
}

@Composable
fun ErrorText(modifier: Modifier = Modifier, title: String, textAlign: TextAlign? = null) {
  Text(
      modifier = modifier,
      text = title,
      textAlign = textAlign,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleSmall)
}

@ThemePreviews
@Composable
fun TitleTextPreview() {
  MainTheme { TitleText(title = "See more") }
}

@ThemePreviews
@Composable
fun ErrorTextPreview() {
  MainTheme { ErrorText(title = "See more") }
}
