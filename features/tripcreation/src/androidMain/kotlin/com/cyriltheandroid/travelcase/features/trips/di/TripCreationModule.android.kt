package com.cyriltheandroid.travelcase.features.trips.di

import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripCreationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val tripCreationModule = module {
    viewModel { TripCreationViewModel(get(), get()) }
}