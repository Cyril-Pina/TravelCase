package com.cyriltheandroid.travelcase.features.tripdetails.model

import com.cyriltheandroid.travelcase.features.model.TripUi
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * @ObjCName is used to set an exact class name for
 * iOS export. Without this annotation, the class name
 * is prefixed by the module name.
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("TripDetailsUiState", exact = true)
data class TripDetailsUiState(
    val isTripLoading: Boolean = true,
    val isFoldersLoading: Boolean = true,
    val tripUi: TripUi = TripUi(),
    val updateTripDetailsUiState: UpdateTripDetailsUiState = UpdateTripDetailsUiState(),
    val tripDetailsFoldersUiState: TripDetailsFoldersUiState = TripDetailsFoldersUiState()
)