package com.cyriltheandroid.travelcase.feature.foldercreation.di

import com.cyriltheandroid.travelcase.feature.foldercreation.viewmodel.FolderCreationViewModel
import org.koin.dsl.module

actual val folderCreationModule = module {
    factory { (tripId: Long) -> FolderCreationViewModel(tripId, get()) }
}