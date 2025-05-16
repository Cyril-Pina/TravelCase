package com.cyriltheandroid.travelcase.core.model.mapper

import com.cyriltheandroid.travelcase.core.model.country.Country
import com.cyriltheandroid.travelcase.designsystem.badge.CountryIntent

fun Country.toCountryIntent(): CountryIntent? {
    return CountryIntent.entries.firstOrNull {
        it.value.code == this.code
    }
}