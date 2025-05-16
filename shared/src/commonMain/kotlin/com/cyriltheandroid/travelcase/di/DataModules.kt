package com.cyriltheandroid.travelcase.di

import com.cyriltheandroid.travelcase.data.files.di.filesDataModule
import com.cyriltheandroid.travelcase.data.travel.di.tripDataModule

val dataModules = listOf(
    tripDataModule,
    filesDataModule,
)