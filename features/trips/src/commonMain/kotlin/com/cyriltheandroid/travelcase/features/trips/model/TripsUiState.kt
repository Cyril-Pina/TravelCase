package com.cyriltheandroid.travelcase.features.trips.model

import com.cyriltheandroid.travelcase.features.model.TripUi
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("TripsUiState", exact = true)
data class TripsUiState(
    val loading: Boolean = true,
    val trips: List<TripUi> = listOf(),
)