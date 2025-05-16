package com.cyriltheandroid.travelcase.domain.travel.model

import com.cyriltheandroid.travelcase.core.model.country.Country
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class Trip(
    val id: Long = 0,
    val title: String = "",
    val country: Country? = null,
    val bannerUri: String = "",
    val creationDate: LocalDateTime? = null,
    val departureDate: LocalDate? = null,
    val returnDate: LocalDate? = null,
    val tripStep: TripStep? = null,
)