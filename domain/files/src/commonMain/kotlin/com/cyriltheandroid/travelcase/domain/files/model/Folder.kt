package com.cyriltheandroid.travelcase.domain.files.model

import kotlinx.datetime.LocalDateTime

data class Folder(
    val id: Long = 0,
    val tripId: Long,
    val title: String,
    val type: FolderType,
    val files: List<File>,
    val creationDate: LocalDateTime,
)