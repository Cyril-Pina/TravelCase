package com.cyriltheandroid.travelcase.designsystem.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.android.designsystem.R
import com.cyriltheandroid.travelcase.designsystem.animation.AnimatedTopToBottomVisibility
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun DropDownTextField(
    value: String,
    items: List<String>,
    onValueChange: (String) -> Unit,
    onItemClick: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    leadingIcon: Painter? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(isFocused) {
        expanded = isFocused
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = label,
            leadingIcon = leadingIcon,
            errorMessage = errorMessage,
            enabled = enabled,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            interactionSource = interactionSource,
        )
        AnimatedTopToBottomVisibility(expanded && items.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .heightIn(max = 160.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFFFFFFF), RoundedCornerShape(32.dp)),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                itemsIndexed(items) { index, item ->
                    DropDownTextFieldItem(
                        text = item,
                        onClick = {
                            onItemClick(index, item)
                            expanded = false
                            focusManager.clearFocus(force = true)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DropDownTextFieldItem(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            text = text,
            intent = TextIntent.Body
        )
    }
}

@Preview
@Composable
fun DropDownTextFieldPreview() {
    var value by remember { mutableStateOf("Bonjour") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DropDownTextField(
            value = value,
            items = listOf(
                "Salut",
                "Salut",
                "Salut",
                "Salut",
                "Salut",
                "Salut",
                "Salut",
            ),
            onValueChange = { value = it },
            leadingIcon = painterResource(R.drawable.ic_globe),
            onItemClick = { _, _ -> }
        )
        TextField(
            value = value,
            onValueChange = { value = it },
        )
    }
}