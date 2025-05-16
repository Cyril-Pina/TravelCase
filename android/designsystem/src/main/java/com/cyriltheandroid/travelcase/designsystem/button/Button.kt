package com.cyriltheandroid.travelcase.designsystem.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.android.designsystem.R
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    intent: ButtonIntent = ButtonIntent.Default,
    enabled: Boolean = true,
    leadingIcon: Painter? = null
) {
    val containerColor = intent.value.containerColor
    val contentColor = intent.value.contentColor

    androidx.compose.material3.Button(
        modifier = modifier
            .heightIn(min = 48.dp)
            .background(
                brush = containerColor,
                shape = RoundedCornerShape(32.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = leadingIcon,
                    contentDescription = "",
                    tint = contentColor
                )
            }
            Text(
                text = text,
                intent = TextIntent.Body,
                color = contentColor
            )
        }
    }
}

enum class ButtonIntent(val value: ButtonIntentValue) {
    Default(value = ButtonIntentValue.Default()),
    Ghost(value = ButtonIntentValue.Ghost()),
    GhostDanger(value = ButtonIntentValue.GhostDanger())
}

sealed class ButtonIntentValue {
    abstract val containerColor: Brush
    abstract val contentColor: Color

    data class Default(
        override val containerColor: Brush = Brush.horizontalGradient(
            colors = listOf(
                Color(0xFF212528),
                Color(0xFF444444)
            )
        ),
        override val contentColor: Color = Color(0xFFFFFFFF)
    ) : ButtonIntentValue()

    data class Ghost(
        override val containerColor: Brush = SolidColor(Color.Transparent),
        override val contentColor: Color = Color(0xFF000000)
    ) : ButtonIntentValue()

    data class GhostDanger(
        override val containerColor: Brush = SolidColor(Color.Transparent),
        override val contentColor: Color = Color(0xFFF90C0C)
    ) : ButtonIntentValue()
}

@Preview
@Composable
fun ButtonPreview(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            text = "Ajouter un fichier",
            onClick = {},
            leadingIcon = painterResource(R.drawable.ic_add)
        )
        Button(
            text = "Ajouter un fichier",
            onClick = {},
            leadingIcon = painterResource(R.drawable.ic_add),
            intent = ButtonIntent.Ghost
        )
        Button(
            text = "Ajouter un fichier",
            onClick = {},
            leadingIcon = painterResource(R.drawable.ic_add),
            intent = ButtonIntent.GhostDanger
        )
    }
}