package com.cyriltheandroid.travelcase.designsystem.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

@Composable
fun IconButton(
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    intent: IconButtonIntent = IconButtonIntent.Default,
    enabled: Boolean = true,
) {
    val contentColor = intent.value.contentColor
    val containerColor = intent.value.containerColor

    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
        androidx.compose.material3.Button(
            modifier = modifier
                .size(48.dp)
                .background(brush = containerColor, shape = RoundedCornerShape(32.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            onClick = onClick,
            enabled = enabled,
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = icon,
                    contentDescription = "",
                    tint = contentColor
                )
            }
        }
    }
}

@Preview
@Composable
fun IconButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            icon = painterResource(R.drawable.ic_check),
            onClick = {}
        )
        IconButton(
            icon = painterResource(R.drawable.ic_left_arrow),
            intent = IconButtonIntent.Secondary,
            onClick = {}
        )
        IconButton(
            icon = painterResource(R.drawable.ic_trash),
            intent = IconButtonIntent.SecondaryDanger,
            onClick = {}
        )
    }
}

enum class IconButtonIntent(val value: IconButtonIntentValue) {
    Default(value = IconButtonIntentValue.Default()),
    Secondary(value = IconButtonIntentValue.Secondary()),
    SecondaryDanger(value = IconButtonIntentValue.SecondaryDanger()),
}

sealed class IconButtonIntentValue {
    abstract val contentColor: Color
    abstract val containerColor: Brush

    data class Default(
        override val contentColor: Color = Color(0xFFFFFFFF),
        override val containerColor: Brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFF212528), Color(0xFF444444))
        )
    ) : IconButtonIntentValue()

    data class Secondary(
        override val contentColor: Color = Color(0xFF000000),
        override val containerColor: Brush = SolidColor(Color.White)
    ) : IconButtonIntentValue()

    data class SecondaryDanger(
        override val contentColor: Color = Color(0xFFF90C0C),
        override val containerColor: Brush = SolidColor(Color.White)
    ) : IconButtonIntentValue()
}