package com.cyriltheandroid.travelcase.features.tripdetails.mapper

import com.cyriltheandroid.core.date.toLocalDate
import com.cyriltheandroid.travelcase.domain.files.model.File
import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.features.model.FileUi
import com.cyriltheandroid.travelcase.features.tripdetails.model.FolderUi
import com.cyriltheandroid.travelcase.features.tripdetails.model.UpdateTripDetailsUiState

fun Trip.updateTrip(
    updateTripDetailsUiState: UpdateTripDetailsUiState
) = this.copy(
    title = updateTripDetailsUiState.title,
    country = updateTripDetailsUiState.selectedCountry,
    departureDate = updateTripDetailsUiState.departureDateMillis?.toLocalDate(),
    returnDate = updateTripDetailsUiState.returnDateMillis?.toLocalDate(),
)

fun Trip.updateTripBanner(
    bannerUri: String
) = this.copy(
    bannerUri = bannerUri
)

fun Folder.toFolderUi() = FolderUi(
    id = this.id,
    title = this.title,
    type = this.type,
    files = this.files.toFilesUi()
)

fun List<File>.toFilesUi() = this.map { file ->
    FileUi(
        id = file.id,
        name = file.name,
        fileUri = file.fileUri
    )
}