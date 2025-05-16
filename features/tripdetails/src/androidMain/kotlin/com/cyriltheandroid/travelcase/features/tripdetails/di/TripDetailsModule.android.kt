package com.cyriltheandroid.travelcase.features.tripdetails.di

import com.cyriltheandroid.travelcase.features.tripdetails.viewmodel.TripDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val tripDetailsModule = module {
    viewModel { (tripId: Long) ->
        TripDetailsViewModel(tripId, get(), get(), get(), get(), get())
    }
}