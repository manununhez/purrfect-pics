package com.manuelnunez.apps.core.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.manuelnunez.apps.core.ui.R
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun BackToolbar(title: String, onBackClick: () -> Unit) {
  Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    IconButton(onClick = onBackClick) {
      Icon(
          imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
          contentDescription = stringResource(id = R.string.button_back),
          tint = MaterialTheme.colorScheme.onSurface)
    }

    SurfaceText(text = title)
  }
}

@ThemePreviews
@Composable
fun BackToolbarPreview() {
  MainTheme { BackToolbar(title = stringResource(id = R.string.section_popular), onBackClick = {}) }
}
