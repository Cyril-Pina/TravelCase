package com.cyriltheandroid.travelcase.features.tripdetails.viewmodel

import com.cyriltheandroid.core.date.DateFormat
import com.cyriltheandroid.core.date.toLocalDate
import com.cyriltheandroid.core.date.toMillis
import com.cyriltheandroid.core.date.toString
import com.cyriltheandroid.core.utils.base.BaseViewModel
import com.cyriltheandroid.travelcase.core.model.country.Country
import com.cyriltheandroid.travelcase.domain.country.usecase.GetAllCountriesUseCase
import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.usecase.GetFoldersFromTripIdUseCase
import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.usecase.DeleteTripUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetTripDetailsUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.UpdateTripDetailsUseCase
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import com.cyriltheandroid.travelcase.features.model.mapper.toUiState
import com.cyriltheandroid.travelcase.features.tripdetails.mapper.toFolderUi
import com.cyriltheandroid.travelcase.features.tripdetails.mapper.updateTrip
import com.cyriltheandroid.travelcase.features.tripdetails.mapper.updateTripBanner
import com.cyriltheandroid.travelcase.features.tripdetails.model.TripDetailsUiState
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
@ObjCName("TripDetailsViewModel", exact = true)
class TripDetailsViewModel(
    private val tripId: Long,
    private val getTripDetailsUseCase: GetTripDetailsUseCase,
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val deleteTripUseCase: DeleteTripUseCase,
    private val updateTripDetailsUseCase: UpdateTripDetailsUseCase,
    private val getFoldersFromTripIdUseCase: GetFoldersFromTripIdUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(TripDetailsUiState())
    val uiState: StateFlow<TripDetailsUiState> = _uiState.asStateFlow()

    private val _event = MutableStateFlow<TripDetailsEvent>(TripDetailsEvent.Undefined)
    val event: StateFlow<TripDetailsEvent> = _event.asStateFlow()

    private lateinit var trip: Trip
    private lateinit var countries: List<Country>
    private lateinit var folders: List<Folder>

    init {
        fetchCountries()
        fetchTripDetails()
    }

    private fun fetchTripDetails() {
        scope.launch {
            getTripDetailsUseCase(tripId).collectLatest {
                trip = it

                fetchFolders()

                val queriedCountry = trip.country?.value.orEmpty()
                val filteredCountries = countries.filter { country ->
                    country.value.contains(
                        queriedCountry,
                        ignoreCase = true
                    )
                }

                _uiState.update { state ->
                    state.copy(
                        isTripLoading = false,
                        tripUi = trip.toUiState(),
                        updateTripDetailsUiState = state.updateTripDetailsUiState.copy(
                            title = trip.title,
                            countries = filteredCountries,
                            selectedCountry = trip.country,
                            queriedCountry = queriedCountry,
                            departureDate = trip.departureDate?.toString(format = DateFormat.FullReadableDateFormatter),
                            returnDate = trip.returnDate?.toString(format = DateFormat.FullReadableDateFormatter),
                            departureDateMillis = trip.departureDate?.toMillis(),
                            returnDateMillis = trip.returnDate?.toMillis(),
                            nextButtonEnabled = false,
                        )
                    )
                }
            }
        }
    }

    private fun fetchCountries() {
        scope.launch {
            countries = getAllCountriesUseCase()
            val filteredCountries = countries.filter { country ->
                country.value.contains(
                    uiState.value.updateTripDetailsUiState.queriedCountry,
                    ignoreCase = true
                )
            }

            _uiState.update { state ->
                state.copy(
                    updateTripDetailsUiState = state.updateTripDetailsUiState.copy(
                        countries = filteredCountries
                    )
                )
            }
        }
    }

    private fun fetchFolders() {
        scope.launch {
            getFoldersFromTripIdUseCase(tripId = trip.id).collectLatest {
                folders = it
                _uiState.update { state ->
                    state.copy(
                        isFoldersLoading = false,
                        tripDetailsFoldersUiState = state.tripDetailsFoldersUiState.copy(
                            folders = folders
                                .sortedByDescending { folder -> folder.creationDate }
                                .map { folder -> folder.toFolderUi() }
                        )
                    )
                }
            }
        }
    }

    fun onDisplayFilePreview(folderId: Long, index: Int, fileUri: String) {
        val folder = folders.firstOrNull { folder ->
            folder.id == folderId && folder.files.any { it.fileUri == fileUri }
        }
        if (folder == null) {
            return
        }

        _event.value = TripDetailsEvent.ClickOnFile(
            filePreviewData = FilePreviewData(
                files = folder.files,
                folderId = folderId,
                initialIndex = index,
            )
        )
    }

    fun onUpdateBanner(fileUri: String?) {
        if (fileUri == null) {
            return
        }

        val updatedTrip = trip.updateTripBanner(fileUri)
        scope.launch {
            updateTripDetailsUseCase(updatedTrip)
        }
    }

    fun onDeleteTrip() {
        scope.launch {
            val filesToDeleteUris = folders.map { folder ->
                folder.files.map { file ->
                    file.fileUri
                }
            }.flatten()

            deleteTripUseCase(trip)
            _event.value = TripDetailsEvent.DeleteTrip(filesToDeleteUris)
        }
    }

    fun onSearchForFolder(query: String) {
        val filteredFolders = folders.filter {
            it.title.contains(query, ignoreCase = true)
                    || it.files.any { it.name.contains(query, ignoreCase = true) }
        }

        _uiState.update { state ->
            state.copy(
                tripDetailsFoldersUiState = state.tripDetailsFoldersUiState.copy(
                    folders = filteredFolders
                        .sortedByDescending { folder -> folder.creationDate }
                        .map { folder -> folder.toFolderUi() }
                )
            )
        }
    }

    fun onFilterCountry(query: String) {
        val filteredCountries = countries.filter { it.value.contains(query, ignoreCase = true) }
        _uiState.update { state ->
            state.copy(
                updateTripDetailsUiState = state.updateTripDetailsUiState.copy(
                    countries = filteredCountries,
                    queriedCountry = query,
                    selectedCountry = null,
                )
            )
        }
    }

    fun onCountrySelected(country: Country) {
        _uiState.update { state ->
            state.copy(
                updateTripDetailsUiState = state.updateTripDetailsUiState.copy(
                    selectedCountry = country,
                    queriedCountry = country.value,
                )
            )
        }

        checkNextButtonEnable()
    }

    fun onUpdateTitle(newTitle: String) {
        _uiState.update { state ->
            state.copy(
                updateTripDetailsUiState = state.updateTripDetailsUiState.copy(
                    title = newTitle,
                )
            )
        }

        checkNextButtonEnable()
    }

    fun onUpdateDates(departureDateMillis: Long?, returnDateMillis: Long?) {
        val departureLocalDate = departureDateMillis.toLocalDate()
        val returnLocalDate = returnDateMillis.toLocalDate()

        val departureDate =
            departureLocalDate?.toString(format = DateFormat.FullReadableDateFormatter)
        val returnDate =
            returnLocalDate?.toString(format = DateFormat.FullReadableDateFormatter)

        _uiState.update { state ->
            state.copy(
                updateTripDetailsUiState = state.updateTripDetailsUiState.copy(
                    departureDate = departureDate,
                    returnDate = returnDate,
                    departureDateMillis = departureDateMillis,
                    returnDateMillis = returnDateMillis,
                )
            )
        }

        checkNextButtonEnable()
    }

    fun onUpdateTrip() {
        scope.launch {
            val updatedTrip = trip.updateTrip(uiState.value.updateTripDetailsUiState)
            updateTripDetailsUseCase(updatedTrip)
        }
    }

    private fun checkNextButtonEnable() {
        val nextButtonEnabled = uiState.value.let { state ->
            state.tripUi.country != state.updateTripDetailsUiState.selectedCountry
                    || state.tripUi.title != state.updateTripDetailsUiState.title
                    || state.tripUi.departureDate != state.updateTripDetailsUiState.departureDate
                    || state.tripUi.returnDate != state.updateTripDetailsUiState.returnDate
        }
        _uiState.update { state ->
            state.copy(
                updateTripDetailsUiState = state.updateTripDetailsUiState.copy(
                    nextButtonEnabled = nextButtonEnabled,
                )
            )
        }
    }

    fun onDispose() {
        _event.value = TripDetailsEvent.Undefined
    }

    @ObjCName("TripDetailsEvent", exact = true)
    sealed class TripDetailsEvent {
        data object Undefined : TripDetailsEvent()
        data class ClickOnFile(val filePreviewData: FilePreviewData) : TripDetailsEvent()
        data class DeleteTrip(val filesToDeleteUris: List<String>) : TripDetailsEvent()
    }
}