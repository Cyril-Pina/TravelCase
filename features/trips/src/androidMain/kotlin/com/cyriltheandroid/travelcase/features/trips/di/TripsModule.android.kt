package com.cyriltheandroid.travelcase.features.trips.di

import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val tripsModule = module {
    viewModel { TripsViewModel(get(), get()) }
}