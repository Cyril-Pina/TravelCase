package com.cyriltheandroid.travelcase.di

import com.cyriltheandroid.travelcase.feature.foldercreation.di.folderCreationModule
import com.cyriltheandroid.travelcase.features.filepreview.di.filePreviewModule
import com.cyriltheandroid.travelcase.features.folderdetails.di.folderDetailsModule
import com.cyriltheandroid.travelcase.features.tripdetails.di.tripDetailsModule
import com.cyriltheandroid.travelcase.features.trips.di.tripCreationModule
import com.cyriltheandroid.travelcase.features.trips.di.tripsModule

val featureModules = listOf(
    tripsModule,
    tripCreationModule,
    tripDetailsModule,
    folderCreationModule,
    folderDetailsModule,
    filePreviewModule,
)