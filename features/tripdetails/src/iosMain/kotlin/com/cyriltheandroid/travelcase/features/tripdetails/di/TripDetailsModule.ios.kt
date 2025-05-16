package com.cyriltheandroid.travelcase.features.tripdetails.di

import com.cyriltheandroid.travelcase.features.tripdetails.viewmodel.TripDetailsViewModel
import org.koin.dsl.module

actual val tripDetailsModule = module {
    factory { (tripId: Long) ->
        TripDetailsViewModel(tripId, get(), get(), get(), get(), get())
    }
}