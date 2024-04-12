package com.manuelnunez.apps.features.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.ui.component.TitleText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.FontScalingPreviews
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
import com.manuelnunez.apps.features.home.ui.R

@Composable
fun HomeErrorScreen(retry: () -> Unit) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column {
      Button(
          onClick = { retry.invoke() },
          colors =
              ButtonDefaults.buttonColors(
                  containerColor = MaterialTheme.colorScheme.onBackground,
              )) {
            Text(text = stringResource(id = R.string.button_retry))
          }

      Text(text = stringResource(id = R.string.alert_error_try_again))
    }
  }
}

@Composable
fun FeatureError() {
  Column {
    TitleText(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp),
        title = stringResource(id = R.string.section_feature))
    Text(text = stringResource(id = R.string.alert_error))
  }
}

@Composable
fun PopularError() {
  Column {
    TitleText(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp),
        title = stringResource(id = R.string.section_popular))
    Text(text = stringResource(id = R.string.alert_error))
  }
}

@FontScalingPreviews
@ThemePreviews
@Composable
private fun HomeErrorScreenPreview() {
  MainTheme { HomeErrorScreen(retry = {}) }
}
