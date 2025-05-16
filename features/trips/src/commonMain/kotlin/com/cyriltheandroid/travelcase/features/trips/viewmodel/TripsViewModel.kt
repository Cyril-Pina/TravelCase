package com.cyriltheandroid.travelcase.features.trips.viewmodel

import com.cyriltheandroid.core.utils.base.BaseViewModel
import com.cyriltheandroid.travelcase.domain.files.usecase.GetTripsFileCountUseCase
import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetAllTripsUseCase
import com.cyriltheandroid.travelcase.features.model.mapper.toUiState
import com.cyriltheandroid.travelcase.features.trips.model.TripsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * @ObjCName is used to set an exact class name for
 * iOS export. Without this annotation, the class name
 * is prefixed by the module name.
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("TripsViewModel", exact = true)
class TripsViewModel(
    private val getAllTripsUseCase: GetAllTripsUseCase,
    private val getTripsFileCountUseCase: GetTripsFileCountUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(TripsUiState())
    val uiState: StateFlow<TripsUiState> = _uiState.asStateFlow()

    private lateinit var trips: List<Trip>

    init {
        scope.launch {
            fetchTrips()
        }
    }

    private suspend fun fetchTrips() {
        getAllTripsUseCase().collectLatest {
            trips = it
            _uiState.update { state ->
                state.copy(
                    loading = false,
                    trips = trips.map { trip -> trip.toUiState() }
                )
            }

            fetchTripsFileCount()
        }
    }

    private suspend fun fetchTripsFileCount() {
        getTripsFileCountUseCase().collectLatest {
            _uiState.update { state ->
                state.copy(
                    trips = state.trips.map { trip ->
                        val fileCount = it.firstOrNull { it.tripId == trip.id }?.fileCount
                        if (fileCount != null) {
                            trip.copy(fileCount = fileCount)
                        } else {
                            trip
                        }
                    }
                )
            }
        }
    }

    fun onSearchForTrip(query: String) {
        if (query.isBlank()) {
            _uiState.update { state ->
                state.copy(
                    loading = false,
                    trips = trips.map { trip -> trip.toUiState() }
                )
            }
            return
        }

        val filteredTrips = trips.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.country?.value?.contains(query, ignoreCase = true) == true
        }
        _uiState.update { state ->
            state.copy(
                loading = false,
                trips = filteredTrips.map { trip -> trip.toUiState() }
            )
        }
    }
}