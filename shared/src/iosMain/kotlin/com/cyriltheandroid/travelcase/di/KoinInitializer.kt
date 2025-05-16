package com.cyriltheandroid.travelcase.di

import com.cyriltheandroid.travelcase.feature.foldercreation.viewmodel.FolderCreationViewModel
import com.cyriltheandroid.travelcase.features.filepreview.viewmodel.FilePreviewViewModel
import com.cyriltheandroid.travelcase.features.folderdetails.viewmodel.FolderDetailsViewModel
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import com.cyriltheandroid.travelcase.features.tripdetails.viewmodel.TripDetailsViewModel
import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripCreationViewModel
import com.cyriltheandroid.travelcase.features.trips.viewmodel.TripsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

fun initKoin() {
    startKoin {
        modules(featureModules + dataModules + domainModules + sharedModules)
    }
}

// This injector is used for iOS app injection.
class ViewModelInjector : KoinComponent {
    val tripsViewModel: TripsViewModel by inject()
    val tripCreationViewModel: TripCreationViewModel by inject()

    fun tripDetailsViewModel(tripId: Long): TripDetailsViewModel {
        return getKoin().get(parameters = { parametersOf(tripId) })
    }

    fun folderCreationViewModel(tripId: Long): FolderCreationViewModel {
        return getKoin().get(parameters = { parametersOf(tripId) })
    }

    fun folderDetailsViewModel(folderId: Long): FolderDetailsViewModel {
        return getKoin().get(parameters = { parametersOf(folderId) })
    }

    fun filePreviewViewModel(filePreviewData: FilePreviewData): FilePreviewViewModel {
        return getKoin().get(parameters = { parametersOf(filePreviewData) })
    }
}