package com.cyriltheandroid.travelcase.features.model

import com.cyriltheandroid.travelcase.core.model.country.Country
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("TripUi", exact = true)
data class TripUi(
    val id: Long = 0,
    val title: String = "",
    val country: Country? = null,
    val bannerUri: String = "",
    val fileCount: Int = 0,
    val daysOfTrip: Int = 0,
    val departureDate: String? = null,
    val returnDate: String? = null,
    val tripStepUi: TripStepUi? = null
)