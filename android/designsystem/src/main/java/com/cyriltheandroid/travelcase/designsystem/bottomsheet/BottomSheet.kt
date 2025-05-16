package com.cyriltheandroid.travelcase.designsystem.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.IconButtonIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    confirmButtonEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = Color(0xFFF2F2F2),
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(
                    icon = painterResource(Icons.Close),
                    onClick = onDismiss,
                    intent = IconButtonIntent.Secondary,
                )
                IconButton(
                    modifier = Modifier.alpha(if (confirmButtonEnabled) 1f else .5f),
                    icon = painterResource(Icons.Check),
                    onClick = onConfirm,
                    enabled = confirmButtonEnabled,
                )
            }
            content()
        }
    }
}