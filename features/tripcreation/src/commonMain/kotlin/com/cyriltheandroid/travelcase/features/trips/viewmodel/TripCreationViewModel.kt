package com.cyriltheandroid.travelcase.features.trips.viewmodel

import com.cyriltheandroid.core.date.DateFormat
import com.cyriltheandroid.core.date.toLocalDate
import com.cyriltheandroid.core.date.toString
import com.cyriltheandroid.core.utils.base.BaseViewModel
import com.cyriltheandroid.travelcase.core.model.country.Country
import com.cyriltheandroid.travelcase.domain.country.usecase.GetAllCountriesUseCase
import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.usecase.AddTravelUseCase
import com.cyriltheandroid.travelcase.features.trips.model.TripCreationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
@ObjCName("TripCreationViewModel", exact = true)
class TripCreationViewModel(
    private val addTravelUseCase: AddTravelUseCase,
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(TripCreationUiState())
    val uiState: StateFlow<TripCreationUiState> = _uiState.asStateFlow()

    private val _event = MutableStateFlow<TripCreationEvent>(TripCreationEvent.Undefined)
    val event: StateFlow<TripCreationEvent> = _event.asStateFlow()

    private lateinit var countries: List<Country>

    init {
        scope.launch {
            countries = getAllCountriesUseCase()
            _uiState.update { state ->
                state.copy(
                    countries = countries
                )
            }
        }
    }

    fun onFilterCountry(query: String) {
        val filteredCountries = countries.filter { it.value.contains(query, ignoreCase = true) }
        _uiState.update { state ->
            state.copy(
                countries = filteredCountries,
                queriedCountry = query,
                selectedCountry = null,
                showTitleTextField = false
            )
        }
    }

    fun onCountrySelected(country: Country) {
        val filteredCountries = countries.filter { it.value.contains(country.value, ignoreCase = true) }

        _uiState.update { state ->
            state.copy(
                countries = filteredCountries,
                selectedCountry = country,
                queriedCountry = country.value,
                showTitleTextField = true,
            )
        }
    }

    fun onUpdateTitle(newTitle: String) {
        _uiState.update { state ->
            state.copy(
                title = newTitle,
                nextButtonEnabled = newTitle.isNotBlank() && state.selectedCountry != null
                        && state.departureDateMillis != null && state.returnDateMillis != null
            )
        }
    }

    fun onUpdateDates(departureDateMillis: Long?, returnDateMillis: Long?) {
        val departureLocalDate = departureDateMillis.toLocalDate()
        val returnLocalDate = returnDateMillis.toLocalDate()

        val departureDate = departureLocalDate?.toString(format = DateFormat.FullReadableDateFormatter)
        val returnDate = returnLocalDate?.toString(format = DateFormat.FullReadableDateFormatter)

        _uiState.update { state ->
            state.copy(
                departureDate = departureDate,
                returnDate = returnDate,
                departureDateMillis = departureDateMillis,
                returnDateMillis = returnDateMillis,
                nextButtonEnabled = state.title.isNotBlank() && state.selectedCountry != null
                        && departureDate != null && returnDate != null
            )
        }
    }

    fun addTrip() {
        scope.launch {
            val trip = Trip(
                title = uiState.value.title,
                country = uiState.value.selectedCountry,
                departureDate = uiState.value.departureDateMillis.toLocalDate(),
                returnDate = uiState.value.returnDateMillis.toLocalDate(),
            )
            val addedTripId = addTravelUseCase(trip)
            _event.value = TripCreationEvent.TripCreated(addedTripId)
        }
    }

    fun onDispose() {
        _event.value = TripCreationEvent.Undefined
    }

    @ObjCName("TripCreationEvent", exact = true)
    sealed class TripCreationEvent {
        data object Undefined : TripCreationEvent()
        data class TripCreated(val tripId: Long) : TripCreationEvent()
    }
}