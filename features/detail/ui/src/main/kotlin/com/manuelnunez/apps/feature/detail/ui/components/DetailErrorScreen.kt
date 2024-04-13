package com.manuelnunez.apps.feature.detail.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.OrientationPreviews
import com.manuelnunez.apps.features.detail.ui.R

@Composable
fun DetailErrorScreen(onBackClick: () -> Unit) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Button(
          onClick = { onBackClick.invoke() },
          colors =
              ButtonDefaults.buttonColors(
                  containerColor = MaterialTheme.colorScheme.onBackground,
              )) {
            Text(text = stringResource(id = R.string.button_back))
          }

      Text(text = stringResource(id = R.string.alert_error_try_again_back), textAlign = TextAlign.Center)
    }
  }
}

@OrientationPreviews
@Composable
fun DetailErrorScreenPreview() {
  MainTheme { DetailErrorScreen(onBackClick = {}) }
}
