package com.cyriltheandroid.travelcase.features.tripdetails.model

import com.cyriltheandroid.travelcase.core.model.country.Country
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("UpdateTripDetailsUiState", exact = true)
data class UpdateTripDetailsUiState(
    val countries: List<Country> = listOf(),
    val title: String = "",
    val selectedCountry: Country? = null,
    val queriedCountry: String = "",
    val nextButtonEnabled: Boolean = false,
    val departureDate: String? = null,
    val returnDate: String? = null,
    val departureDateMillis: Long? = null,
    val returnDateMillis: Long? = null,
)