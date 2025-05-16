package com.cyriltheandroid.travelcase.features.folderdetails.mapper

import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.features.folderdetails.model.UpdateFolderDetailsUiState

fun Folder.updateFolder(
    updateFolderDetailsUiState: UpdateFolderDetailsUiState,
) = this.copy(
    title = updateFolderDetailsUiState.title,
    type = requireNotNull(updateFolderDetailsUiState.selectedFolderType),
)