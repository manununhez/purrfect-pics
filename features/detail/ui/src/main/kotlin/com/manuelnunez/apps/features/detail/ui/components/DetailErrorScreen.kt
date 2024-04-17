package com.manuelnunez.apps.features.detail.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.manuelnunez.apps.core.ui.component.ErrorDialog
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.OrientationPreviews
import com.manuelnunez.apps.features.detail.ui.R
import com.manuelnunez.apps.core.ui.R as RCU

@Composable
fun DetailErrorScreen(onBackClick: () -> Unit) {
  ErrorDialog(
      onConfirmClick = onBackClick,
      dialogTitle = stringResource(id = RCU.string.alert_error_title),
      dialogText = stringResource(id = R.string.alert_error_try_again_back))
}

@OrientationPreviews
@Composable
fun DetailErrorScreenPreview() {
  MainTheme { DetailErrorScreen(onBackClick = {}) }
}
