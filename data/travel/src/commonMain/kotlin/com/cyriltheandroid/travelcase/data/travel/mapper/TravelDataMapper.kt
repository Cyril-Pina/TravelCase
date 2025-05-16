package com.cyriltheandroid.travelcase.data.travel.mapper

import com.cyriltheandroid.core.database.entity.TripEntity
import com.cyriltheandroid.core.date.DateFormat
import com.cyriltheandroid.core.date.toLocalDate
import com.cyriltheandroid.core.date.toLocalDateTime
import com.cyriltheandroid.core.date.toString
import com.cyriltheandroid.travelcase.core.model.country.Country
import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.raedghazal.kotlinx_datetime_ext.now
import kotlinx.datetime.LocalDateTime

fun TripEntity.toTrip() = Trip(
    id = this.id,
    title = this.title,
    country = Country.fromCode(this.countryCode),
    bannerUri = this.bannerUri.orEmpty(),
    creationDate = this.creationDate?.toLocalDateTime(DateFormat.SqlFormat),
    departureDate = this.departureDate?.toLocalDate(DateFormat.SqlFormat),
    returnDate = this.returnDate?.toLocalDate(DateFormat.SqlFormat),
)

fun Trip.toTripEntity() = TripEntity(
    id = this.id,
    title = this.title,
    countryCode = this.country?.code ?: "",
    bannerUri = this.bannerUri,
    creationDate = LocalDateTime.now().toString(DateFormat.SqlFormat),
    departureDate = this.departureDate?.toLocalDateTime()?.toString(DateFormat.SqlFormat),
    returnDate = this.returnDate?.toLocalDateTime()?.toString(DateFormat.SqlFormat),
)