package com.cyriltheandroid.travelcase.android.tripcreation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.IconButtonIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripCreationViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripCreationScreen(
    onBackClick: () -> Unit,
    onTripCreated: (tripId: Long) -> Unit,
    modifier: Modifier = Modifier,
    tripCreationViewModel: TripCreationViewModel = koinViewModel()
) {
    val uiState by tripCreationViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        tripCreationViewModel.event.collectLatest { event ->
            when (event) {
                is TripCreationViewModel.TripCreationEvent.TripCreated -> {
                    onTripCreated(event.tripId)
                    tripCreationViewModel.onDispose()
                }

                TripCreationViewModel.TripCreationEvent.Undefined -> Unit
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {},
                navigationIcon = {
                    IconButton(
                        icon = painterResource(Icons.LeftArrow),
                        onClick = onBackClick,
                        intent = IconButtonIntent.Secondary
                    )
                },
                actions = {
                    IconButton(
                        modifier = Modifier.alpha(if (uiState.nextButtonEnabled) 1f else .5f),
                        icon = painterResource(Icons.Check),
                        onClick = tripCreationViewModel::addTrip,
                        enabled = uiState.nextButtonEnabled
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
    ) { innerPadding ->
        TripCreationContent(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F2F2))
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp),
            onDropDownValueChange = tripCreationViewModel::onFilterCountry,
            onCountrySelected = { index ->
                tripCreationViewModel.onCountrySelected(uiState.countries[index])
            },
            onTextFieldValueChange = tripCreationViewModel::onUpdateTitle,
            onUpdateDates = tripCreationViewModel::onUpdateDates,
            queriedCountry = uiState.queriedCountry,
            countries = uiState.countries,
            showTitleTextField = uiState.showTitleTextField,
            tripTitle = uiState.title,
            departureDate = uiState.departureDate,
            returnDate = uiState.returnDate,
            departureDateMillis = uiState.departureDateMillis,
            returnDateMillis = uiState.returnDateMillis,
        )
    }
}

@Preview
@Composable
fun TripCreationScreenPreview() {
    TripCreationScreen(
        modifier = Modifier.fillMaxSize(),
        onTripCreated = {},
        onBackClick = {}
    )
}