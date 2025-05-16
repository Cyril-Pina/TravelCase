package com.cyriltheandroid.travelcase.features.trips.di

import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripCreationViewModel
import org.koin.dsl.module

actual val tripCreationModule = module {
    factory { TripCreationViewModel(get(), get()) }
}