package com.cyriltheandroid.travelcase.navigation

sealed class Screen(val route: String) {
    data object Trips : Screen("trips")
    data object TripCreation : Screen("trip_creation")
    data object TripDetails: Screen("trip_details/{tripId}")
    data object FolderCreation: Screen("folder_creation/{tripId}")
    data object FolderDetails: Screen("folder_details/{folderId}")
    data object FilePreview: Screen("file_preview/{folderId}")
}