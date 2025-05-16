package com.cyriltheandroid.travelcase

import android.app.Application
import com.cyriltheandroid.travelcase.di.initKoin

class TravelCaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(this)
    }
}