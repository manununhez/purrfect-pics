package com.manuelnunez.apps.core.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
  Text(
      modifier = modifier,
      text = title,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleLarge)
}

@ThemePreviews
@Composable
fun TitleTextPreview() {
  MainTheme { TitleText(title = "See more") }
}
