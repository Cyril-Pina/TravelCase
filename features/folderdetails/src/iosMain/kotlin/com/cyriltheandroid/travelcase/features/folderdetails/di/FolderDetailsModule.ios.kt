package com.cyriltheandroid.travelcase.features.folderdetails.di

import com.cyriltheandroid.travelcase.features.folderdetails.viewmodel.FolderDetailsViewModel
import org.koin.dsl.module

actual val folderDetailsModule = module {
    factory { (folderId: Long) ->
        FolderDetailsViewModel(folderId, get(), get(), get(), get(), get())
    }
}