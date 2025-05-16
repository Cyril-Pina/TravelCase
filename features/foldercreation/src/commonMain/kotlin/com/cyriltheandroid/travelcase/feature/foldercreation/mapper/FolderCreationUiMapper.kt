package com.cyriltheandroid.travelcase.feature.foldercreation.mapper

import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.feature.foldercreation.model.FolderCreationUiState
import com.cyriltheandroid.travelcase.features.model.mapper.toFiles
import com.raedghazal.kotlinx_datetime_ext.now
import kotlinx.datetime.LocalDateTime

fun FolderCreationUiState.toFolder(tripId: Long) = Folder(
    tripId = tripId,
    title = this.title,
    type = requireNotNull(this.selectedFolderType),
    files = this.files.toFiles(),
    creationDate = LocalDateTime.now(),
)