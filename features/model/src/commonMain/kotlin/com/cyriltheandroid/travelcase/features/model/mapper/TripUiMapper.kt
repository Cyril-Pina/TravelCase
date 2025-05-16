package com.cyriltheandroid.travelcase.features.model.mapper

import com.cyriltheandroid.core.date.DateFormat
import com.cyriltheandroid.core.date.durationUntil
import com.cyriltheandroid.core.date.toString
import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.model.TripStep
import com.cyriltheandroid.travelcase.features.model.TripStepUi
import com.cyriltheandroid.travelcase.features.model.TripUi

fun Trip.toUiState() = TripUi(
    id = id,
    title = title,
    country = country,
    bannerUri = bannerUri,
    departureDate = departureDate?.toString(format = DateFormat.FullReadableDateFormatter),
    returnDate = returnDate?.toString(format = DateFormat.FullReadableDateFormatter),
    daysOfTrip = returnDate?.let {
        departureDate?.durationUntil(it)?.inWholeDays?.toInt()?.plus(1)
    } ?: 0,
    tripStepUi = tripStep?.toTripStepUi(),
)

fun TripStep.toTripStepUi(): TripStepUi {
    return when (this) {
        is TripStep.Incoming -> TripStepUi.Incoming(this.daysLeft)
        TripStep.Pending -> TripStepUi.Pending
        TripStep.Finished -> TripStepUi.Finished
    }
}