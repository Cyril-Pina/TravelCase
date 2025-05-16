package com.cyriltheandroid.travelcase.features.folderdetails.model

import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.features.model.FileUi
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * @ObjCName is used to set an exact class name for
 * iOS export. Without this annotation, the class name
 * is prefixed by the module name.
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("FolderDetailsUiState", exact = true)
data class FolderDetailsUiState(
    val isLoading: Boolean = true,
    val title: String = "",
    val type: FolderType? = null,
    val showFullButton: Boolean = true,
    val files: List<FileUi> = listOf(),
    val updateFolderDetailsUiState: UpdateFolderDetailsUiState = UpdateFolderDetailsUiState(),
)
