package com.cyriltheandroid.travelcase.designsystem.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun FileCountBadge(
    count: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Color(0xFFEBEBEB), CircleShape),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(Icons.Files),
                contentDescription = "",
                tint = Color(0xFF000000)
            )
            Text(
                text = count.toString(),
                intent = TextIntent.Small,
                color = Color(0xFF000000)
            )
        }
    }
}

@Preview
@Composable
fun DocumentCountBadgePreview() {
    FileCountBadge(count = 3)
}