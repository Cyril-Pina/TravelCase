package com.cyriltheandroid.travelcase.android.trips

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.core.model.mapper.toCountryIntent
import com.cyriltheandroid.travelcase.designsystem.badge.TripStepIntent
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.card.TripCard
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.textfield.TextField
import com.cyriltheandroid.travelcase.features.model.TripStepUi
import com.cyriltheandroid.travelcase.features.model.TripUi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TripsContent(
    trips: List<TripUi>,
    modifier: Modifier = Modifier,
    onAddTripClick: () -> Unit,
    onTripClick: (tripId: Long) -> Unit,
    onSearchForTrip: (query: String) -> Unit,
) {
    val navigationBars = WindowInsets.navigationBars.asPaddingValues()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = navigationBars.calculateBottomPadding() + 16.dp)
    ) {
        stickyHeader {
            TripSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2F2F2))
                    .padding(16.dp),
                onAddTripClick = onAddTripClick,
                onSearchForTrip = onSearchForTrip,
            )
        }

        items(trips, key = { it.id }) { trip ->
            TripCard(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .animateItem(),
                title = trip.title,
                subtitle = if (trip.departureDate != null && trip.returnDate != null) {
                    pluralStringResource(
                        R.plurals.trip_card_subtitle,
                        trip.daysOfTrip,
                        requireNotNull(trip.departureDate),
                        requireNotNull(trip.returnDate),
                        trip.daysOfTrip
                    )
                } else "",
                bannerUri = trip.bannerUri,
                onClick = { onTripClick(trip.id) },
                countryIntent = trip.country?.toCountryIntent(),
                fileCount = trip.fileCount,
                tripStepIntent = when (val step = trip.tripStepUi) {
                    is TripStepUi.Incoming -> TripStepIntent.Incoming(step.daysLeft)
                    TripStepUi.Pending -> TripStepIntent.Pending
                    TripStepUi.Finished -> TripStepIntent.Finished
                    null -> null
                }
            )
        }
    }
}

@Composable
private fun TripSearchBar(
    onAddTripClick: () -> Unit,
    onSearchForTrip: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            icon = painterResource(Icons.Add),
            onClick = onAddTripClick
        )
        TextField(
            label = stringResource(R.string.search_trip_label),
            value = value,
            onValueChange = {
                value = it
                onSearchForTrip(it)
            },
            leadingIcon = painterResource(Icons.Search)
        )
    }
}