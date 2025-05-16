package com.cyriltheandroid.travelcase.navigation

import androidx.navigation.NavHostController
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal const val FILE_PREVIEW_SAVED_STATE_KEY = "files_uris"

fun NavHostController.navigateToTripCreation() {
    navigate(Screen.TripCreation.route)
}

fun NavHostController.navigateToTripDetails(tripId: Long) {
    navigate(Screen.TripDetails.route.replace("{tripId}", tripId.toString()))
}

fun NavHostController.navigateToFolderCreation(tripId: Long?) {
    navigate(Screen.FolderCreation.route.replace("{tripId}", tripId.toString()))
}

fun NavHostController.navigateToFolderDetails(folderId: Long?) {
    navigate(Screen.FolderDetails.route.replace("{folderId}", folderId.toString()))
}

fun NavHostController.navigateToFilePreview(filePreviewData: FilePreviewData) {
    val json = Json.encodeToString(filePreviewData)
    currentBackStackEntry?.savedStateHandle?.set(FILE_PREVIEW_SAVED_STATE_KEY, json)
    navigate(Screen.FilePreview.route)
}

fun NavHostController.getFilePreviewSaveStateData(): FilePreviewData {
    val json = previousBackStackEntry?.savedStateHandle?.get<String>(FILE_PREVIEW_SAVED_STATE_KEY)
    return json?.let { Json.decodeFromString<FilePreviewData>(it) } ?: FilePreviewData()
}

fun NavHostController.emptySaveStateHandle() {
    currentBackStackEntry?.savedStateHandle?.let {
        it.keys().forEach { key ->
            it.remove<Any>(key)
        }
    }
}