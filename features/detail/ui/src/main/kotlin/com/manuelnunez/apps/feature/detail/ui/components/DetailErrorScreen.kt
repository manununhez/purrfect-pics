package com.manuelnunez.apps.feature.detail.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.manuelnunez.apps.core.ui.component.TitleText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.OrientationPreviews
import com.manuelnunez.apps.core.ui.R as RCU

@Composable
fun DetailErrorScreen(onBackClick: () -> Unit) {
  val textButtonGoBack = stringResource(id = RCU.string.button_back)
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))

      Button(
          modifier = Modifier.semantics { contentDescription = textButtonGoBack },
          onClick = { onBackClick.invoke() },
          colors =
              ButtonDefaults.buttonColors(
                  containerColor = MaterialTheme.colorScheme.onBackground,
              )) {
            Text(text = textButtonGoBack)
          }

      TitleText(
          title = stringResource(id = RCU.string.alert_error_try_again_back),
          textAlign = TextAlign.Center)

      Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
  }
}

@OrientationPreviews
@Composable
fun DetailErrorScreenPreview() {
  MainTheme { DetailErrorScreen(onBackClick = {}) }
}
