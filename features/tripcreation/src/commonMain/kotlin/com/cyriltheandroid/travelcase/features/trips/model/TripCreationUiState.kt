package com.cyriltheandroid.travelcase.features.trips.model

import com.cyriltheandroid.travelcase.core.model.country.Country
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("TripCreationUiState", exact = true)
data class TripCreationUiState(
    val countries: List<Country> = listOf(),
    val selectedCountry: Country? = null,
    val title: String = "",
    val queriedCountry: String = "",
    val showTitleTextField: Boolean = false,
    val nextButtonEnabled: Boolean = false,
    val departureDate: String? = null,
    val returnDate: String? = null,
    val departureDateMillis: Long? = null,
    val returnDateMillis: Long? = null,
)