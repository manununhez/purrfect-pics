package com.manuelnunez.apps.core.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import com.manuelnunez.apps.core.ui.theme.MainTheme
import com.manuelnunez.apps.core.ui.utils.ThemePreviews

@Composable
fun ErrorDialog(dialogTitle: String, dialogText: String, onConfirmation: () -> Unit) {
  val openAlertDialog = remember { mutableStateOf(true) }

  when {
    openAlertDialog.value -> {
      ErrorAlertDialog(
          onDismissRequest = { openAlertDialog.value = false },
          onConfirmation = {
            openAlertDialog.value = false
            onConfirmation.invoke()
          },
          dialogTitle = dialogTitle,
          dialogText = dialogText,
          icon = Icons.Default.Info)
    }
  }
}

@Composable
fun ErrorAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
  AlertDialog(
      icon = { Icon(icon, contentDescription = "Example Icon") },
      title = { Text(text = dialogTitle) },
      text = { Text(text = dialogText) },
      onDismissRequest = { onDismissRequest() },
      confirmButton = { TextButton(onClick = { onConfirmation() }) { Text("Confirm") } },
      dismissButton = { TextButton(onClick = { onDismissRequest() }) { Text("Dismiss") } })
}

@ThemePreviews
@Composable
fun ErrorDialogPreview() {
  MainTheme {
    ErrorDialog(
        onConfirmation = {},
        dialogTitle = "Title",
        dialogText = "https://picsum.photos/id/237/200/300")
  }
}
