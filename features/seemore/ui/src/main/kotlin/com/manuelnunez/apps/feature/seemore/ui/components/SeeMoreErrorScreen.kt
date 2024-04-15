package com.manuelnunez.apps.feature.seemore.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.manuelnunez.apps.core.ui.component.ErrorDialog
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.FontScalingPreviews
import com.manuelnunez.apps.core.ui.utils.ThemePreviews
import com.manuelnunez.apps.core.ui.R as RCU

@Composable
fun SeeMoreErrorScreen(retry: () -> Unit) {
  ErrorDialog(
      onConfirmation = retry,
      dialogTitle = stringResource(id = RCU.string.alert_error_title),
      dialogText = stringResource(id = RCU.string.alert_error_try_again))
}

@FontScalingPreviews
@ThemePreviews
@Composable
private fun SeeMoreErrorScreenPreview() {
  MainTheme { SeeMoreErrorScreen(retry = {}) }
}
