package com.cyriltheandroid.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,

    @ColumnInfo(name = "country_code")
    val countryCode: String,

    @ColumnInfo(name = "banner_uri")
    val bannerUri: String? = null,

    @ColumnInfo(name = "creation_date")
    val creationDate: String? = null,

    @ColumnInfo(name = "departure_date")
    val departureDate: String? = null,

    @ColumnInfo(name = "return_date")
    val returnDate: String? = null,
)