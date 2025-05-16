package com.cyriltheandroid.travelcase.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(androidContext: Context) {
    startKoin {
        androidContext(androidContext)
        modules(featureModules + dataModules + domainModules + sharedModules)
    }
}