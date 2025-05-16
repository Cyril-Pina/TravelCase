package com.cyriltheandroid.travelcase.di

import com.cyriltheandroid.travelcase.domain.country.di.countryDomainModule
import com.cyriltheandroid.travelcase.domain.files.di.filesDomainModule
import com.cyriltheandroid.travelcase.domain.travel.di.tripDomainModule

val domainModules = listOf(
    tripDomainModule,
    countryDomainModule,
    filesDomainModule,
)