package com.cyriltheandroid.travelcase.designsystem.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun SmallBadge(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(16.dp)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            intent = TextIntent.Small,
            textAlign = TextAlign.Center
        )
    }
}