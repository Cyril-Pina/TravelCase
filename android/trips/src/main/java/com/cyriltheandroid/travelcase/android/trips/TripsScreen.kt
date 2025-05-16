package com.cyriltheandroid.travelcase.android.trips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent
import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsScreen(
    onAddTripClick: () -> Unit,
    onTripClick: (tripId: Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TripsViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.trips_top_title),
                        intent = TextIntent.H3,
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
    ) { innerPadding ->
        if (uiState.loading) {
            TripsLoading(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF2F2F2))
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(16.dp),
            )
        } else {
            TripsContent(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF2F2F2))
                    .padding(top = innerPadding.calculateTopPadding()),
                trips = uiState.trips,
                onAddTripClick = onAddTripClick,
                onTripClick = onTripClick,
                onSearchForTrip = viewModel::onSearchForTrip,
            )
        }
    }
}