package com.cyriltheandroid.travelcase.features.trips.di

import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripsViewModel
import org.koin.dsl.module

actual val tripsModule = module {
    single { TripsViewModel(get(), get()) }
}