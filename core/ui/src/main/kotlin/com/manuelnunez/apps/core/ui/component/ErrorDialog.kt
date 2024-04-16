package com.manuelnunez.apps.core.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.manuelnunez.apps.core.ui.R
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun ErrorDialog(
    dialogTitle: String,
    dialogText: String,
    onConfirmClick: () -> Unit,
    icon: ImageVector = Icons.Default.Warning,
    confirmButtonText: String = stringResource(id = R.string.alert_dialog_confirm_button),
    dismissButtonText: String = stringResource(id = R.string.alert_dialog_dismiss_button)
) {
  val openAlertDialog = remember { mutableStateOf(true) }

  val onDismissRequest = { openAlertDialog.value = false }

  val onConfirmation = {
    openAlertDialog.value = false
    onConfirmClick.invoke()
  }

  when {
    openAlertDialog.value -> {
      AlertDialog(
          icon = { Icon(icon, contentDescription = null) },
          title = { Text(text = dialogTitle) },
          text = { Text(text = dialogText) },
          onDismissRequest = { onDismissRequest() },
          confirmButton = {
            TextButton(
                modifier = Modifier.semantics { contentDescription = confirmButtonText },
                onClick = { onConfirmation() }) {
                  Text(confirmButtonText)
                }
          },
          dismissButton = {
            TextButton(
                modifier = Modifier.semantics { contentDescription = dismissButtonText },
                onClick = { onDismissRequest() }) {
                  Text(dismissButtonText)
                }
          })
    }
  }
}

@ThemePreviews
@Composable
fun ErrorDialogPreview() {
  MainTheme {
    ErrorDialog(
        onConfirmClick = {},
        dialogTitle = "Title",
        dialogText = "https://picsum.photos/id/237/200/300")
  }
}
