package com.cyriltheandroid.travelcase.features.folderdetails.di

import com.cyriltheandroid.travelcase.features.folderdetails.viewmodel.FolderDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val folderDetailsModule = module {
    viewModel { (folderId: Long) ->
        FolderDetailsViewModel(folderId, get(), get(), get(), get(), get())
    }
}