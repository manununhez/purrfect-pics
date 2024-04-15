package com.manuelnunez.apps.features.home.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.manuelnunez.apps.core.ui.component.ErrorDialog
import com.manuelnunez.apps.core.ui.component.ErrorText
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.FontScalingPreviews
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
import com.manuelnunez.apps.features.home.ui.R
import com.manuelnunez.apps.core.ui.R as RCU

@Composable
fun HomeErrorScreen(retry: () -> Unit) {
  ErrorDialog(
      onConfirmation = retry,
      dialogTitle = stringResource(id = RCU.string.alert_error_title),
      dialogText = stringResource(id = RCU.string.alert_error_try_again))
}

@Composable
fun FeatureError() {
  ErrorText(
      modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp).fillMaxWidth(),
      textAlign = TextAlign.Center,
      title = stringResource(id = R.string.alert_error_feature))
}

@Composable
fun PopularError() {
  ErrorText(
      modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp).fillMaxWidth(),
      textAlign = TextAlign.Center,
      title = stringResource(id = R.string.alert_error_popular))
}

@FontScalingPreviews
@ThemePreviews
@Composable
private fun HomeErrorScreenPreview() {
  MainTheme { HomeErrorScreen(retry = {}) }
}
