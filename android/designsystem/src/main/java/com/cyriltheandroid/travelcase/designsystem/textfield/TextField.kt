package com.cyriltheandroid.travelcase.designsystem.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cyriltheandroid.travelcase.android.designsystem.R
import com.cyriltheandroid.travelcase.designsystem.modifier.clearFocusOnKeyboardDismiss
import com.cyriltheandroid.travelcase.designsystem.modifier.conditional
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    leadingIcon: Painter? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    var mValue by remember(value) { mutableStateOf(value) }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFFFFFFF), CircleShape)
                .conditional(condition = errorMessage != null) {
                    border(1.dp, Color(0xFFF90C0C), CircleShape)
                }
                .padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                leadingIcon?.let { leadingIcon ->
                    Icon(
                        modifier = Modifier.size(14.dp),
                        painter = leadingIcon,
                        contentDescription = "",
                        tint = if (errorMessage != null) Color(0xFFF90C0C) else Color(0xFF9E9E9E)
                    )
                }
                BasicTextField(
                    modifier = Modifier
                        .clearFocusOnKeyboardDismiss()
                        .weight(1f),
                    value = if (label != null && !isFocused && mValue.isEmpty()) {
                        label
                    } else {
                        mValue
                    },
                    onValueChange = onValueChange,
                    enabled = enabled,
                    readOnly = readOnly,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    interactionSource = interactionSource,
                    singleLine = singleLine,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = if (label != null && !isFocused && mValue.isEmpty()) {
                            Color(0xFF9E9E9E)
                        } else {
                            Color(0xFF000000)
                        }
                    )
                )
                if (isFocused && mValue.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .size(14.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    mValue = ""
                                    onValueChange("")
                                }
                            ),
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = "",
                        tint = Color(0xFF000000)
                    )
                }
            }
        }
        errorMessage?.let { errorMessage ->
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = errorMessage,
                intent = TextIntent.Body,
                color = Color(0xFFF90C0C),
            )
        }
    }
}

@Preview
@Composable
fun TextFieldPreview() {
    var value by remember { mutableStateOf("Bonjour") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            errorMessage = "Une erreur est apparue",
            leadingIcon = painterResource(R.drawable.ic_globe),
            onValueChange = { value = it }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            leadingIcon = painterResource(R.drawable.ic_home),
            onValueChange = { value = it }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = "Label",
            value = "",
            leadingIcon = painterResource(R.drawable.ic_home),
            onValueChange = { value = it }
        )
    }
}