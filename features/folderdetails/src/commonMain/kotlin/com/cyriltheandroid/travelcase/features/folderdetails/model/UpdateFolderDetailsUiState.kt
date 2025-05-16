package com.cyriltheandroid.travelcase.features.folderdetails.model

import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("UpdateFolderDetailsUiState", exact = true)
data class UpdateFolderDetailsUiState(
    val title: String = "",
    val selectedFolderType: FolderType? = null,
    val nextButtonEnabled: Boolean = false,
)
