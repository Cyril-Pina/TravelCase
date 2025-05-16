package com.cyriltheandroid.travelcase.designsystem.alertdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.designsystem.button.Button
import com.cyriltheandroid.travelcase.designsystem.button.ButtonIntent
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.IconButtonIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(
    title: String,
    subtitle: String,
    actionButtonText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    actionButtonIcon: Painter? = null,
) {
    BasicAlertDialog(
        modifier = modifier.background(
            color = Color(0xFFF2F2F2),
            shape = RoundedCornerShape(32.dp)
        ),
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    icon = painterResource(Icons.Close),
                    onClick = onDismiss,
                    intent = IconButtonIntent.Secondary,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                intent = TextIntent.SmallBold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = subtitle,
                intent = TextIntent.Body,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                text = actionButtonText,
                leadingIcon = actionButtonIcon,
                onClick = onConfirm,
                intent = ButtonIntent.GhostDanger,
            )
        }
    }
}

@Preview
@Composable
fun AlertDialogPreview(modifier: Modifier = Modifier) {
    AlertDialog(
        title = "Suppression du voyage \"Brésil\"",
        subtitle = "Confirmez-vous la suppression du voyage \"Brésil\" ? Cette action est irréversible.",
        onDismiss = {},
        onConfirm = {},
        actionButtonText = "Confirmer",
        actionButtonIcon = painterResource(Icons.Trash)
    )
}