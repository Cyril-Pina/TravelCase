package com.cyriltheandroid.travelcase.feature.foldercreation.di

import com.cyriltheandroid.travelcase.feature.foldercreation.viewmodel.FolderCreationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val folderCreationModule = module {
    viewModel { (tripId: Long) -> FolderCreationViewModel(tripId, get()) }
}