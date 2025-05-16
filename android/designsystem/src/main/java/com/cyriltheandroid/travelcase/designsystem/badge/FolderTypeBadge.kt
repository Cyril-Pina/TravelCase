package com.cyriltheandroid.travelcase.designsystem.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.android.designsystem.R
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.modifier.conditional
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun FolderTypeBadge(
    intent: FolderTypeIntent,
    modifier: Modifier = Modifier,
    clickable: Boolean = false,
    onClick: () -> Unit = {},
) {
    val titleResId = intent.value.titleResId
    val iconResId = intent.value.iconResId
    val containerColor = intent.value.containerColor
    val borderColor = intent.value.borderColor

    Box(
        modifier = modifier
            .clip(CircleShape)
            .conditional(clickable) {
                clickable(onClick = onClick)
            }
            .border(1.dp, borderColor, CircleShape)
            .background(containerColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = "",
                tint = borderColor
            )
            Text(
                text = stringResource(titleResId),
                intent = TextIntent.Small,
                color = borderColor
            )
        }
    }
}

@Preview
@Composable
fun FolderTypeBadgePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FolderTypeBadge(intent = FolderTypeIntent.Ticket, onClick = {})
        FolderTypeBadge(intent = FolderTypeIntent.Accommodation, onClick = {})
        FolderTypeBadge(intent = FolderTypeIntent.VehicleRental, onClick = {})
        FolderTypeBadge(intent = FolderTypeIntent.Other, onClick = {})
    }
}

enum class FolderTypeIntent(val value: FolderTypeIntentValue) {
    Ticket(value = FolderTypeIntentValue.Ticket()),
    Accommodation(value = FolderTypeIntentValue.Accommodation()),
    VehicleRental(value = FolderTypeIntentValue.VehicleRental()),
    Other(value = FolderTypeIntentValue.Other()),
}

sealed class FolderTypeIntentValue {
    abstract val iconResId: Int
    abstract val titleResId: Int
    abstract val containerColor: Color
    abstract val borderColor: Color

    data class Ticket(
        override val iconResId: Int = R.drawable.ic_plane,
        override val titleResId: Int = R.string.travel_type_ticket_label,
        override val containerColor: Color = Color(0xFFFAD2D2),
        override val borderColor: Color = Color(0xFFE63946),
    ) : FolderTypeIntentValue()

    data class Accommodation(
        override val iconResId: Int = Icons.Home,
        override val titleResId: Int = R.string.travel_type_accommodation_label,
        override val containerColor: Color = Color(0xFFA9D6E5),
        override val borderColor: Color = Color(0xFF0077B6),
    ) : FolderTypeIntentValue()

    data class VehicleRental(
        override val iconResId: Int = Icons.Car,
        override val titleResId: Int = R.string.travel_type_rental_label,
        override val containerColor: Color = Color(0xFFE4CAFF),
        override val borderColor: Color = Color(0xFF9B5DE5),
    ) : FolderTypeIntentValue()

    data class Other(
        override val iconResId: Int = Icons.Folder,
        override val titleResId: Int = R.string.travel_type_others_label,
        override val containerColor: Color = Color(0xFFA8DCD1),
        override val borderColor: Color = Color(0xFF1B8E80),
    ) : FolderTypeIntentValue()
}