package com.cyriltheandroid.travelcase.features.filepreview.di

import com.cyriltheandroid.travelcase.features.filepreview.viewmodel.FilePreviewViewModel
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import org.koin.dsl.module

actual val filePreviewModule = module {
    factory { (filePreviewData: FilePreviewData) ->
        FilePreviewViewModel(filePreviewData, get())
    }
}