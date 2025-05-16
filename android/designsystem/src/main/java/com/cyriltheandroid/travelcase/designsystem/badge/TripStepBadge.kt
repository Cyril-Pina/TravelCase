package com.cyriltheandroid.travelcase.designsystem.badge

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.android.designsystem.R
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun TripStepBadge(
    intent: TripStepIntent,
    modifier: Modifier = Modifier,
) {
    val value: TripStepIntentValue = when (intent) {
        is TripStepIntent.Incoming -> TripStepIntentValue.Incoming(intent.daysLeft)
        TripStepIntent.Pending -> TripStepIntentValue.Pending()
        TripStepIntent.Finished -> TripStepIntentValue.Finished()
    }

    Box(
        modifier = modifier.background(value.backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(value.iconResId),
                contentDescription = "",
                tint = Color(0xFFFFFFFF)
            )
            Text(
                text = if (value is TripStepIntentValue.Incoming) {
                    pluralStringResource(value.textResId, value.daysLeft, value.daysLeft)
                } else {
                    stringResource(value.textResId)
                },
                intent = TextIntent.Small,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}

@Preview
@Composable
fun TravelStepBadgePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TripStepBadge(
            intent = TripStepIntent.Incoming(5)
        )
        TripStepBadge(
            intent = TripStepIntent.Pending
        )
        TripStepBadge(
            intent = TripStepIntent.Finished
        )
    }
}

sealed interface TripStepIntent {
    data class Incoming(val daysLeft: Int) : TripStepIntent
    data object Pending : TripStepIntent
    data object Finished : TripStepIntent
}

private sealed class TripStepIntentValue {
    abstract val textResId: Int
    abstract val iconResId: Int
    abstract val backgroundColor: Color

    data class Incoming(
        val daysLeft: Int,
        override val textResId: Int = R.plurals.travel_state_incoming,
        override val iconResId: Int = Icons.Warning,
        override val backgroundColor: Color = Color(0xFFCA5F07)
    ) : TripStepIntentValue()

    data class Pending(
        override val textResId: Int = R.string.travel_state_pending,
        override val iconResId: Int = Icons.Plane,
        override val backgroundColor: Color = Color(0xFF0077B6)
    ) : TripStepIntentValue()

    data class Finished(
        override val textResId: Int = R.string.travel_state_finished,
        override val iconResId: Int = Icons.Check,
        override val backgroundColor: Color = Color(0xFF000000)
    ) : TripStepIntentValue()
}