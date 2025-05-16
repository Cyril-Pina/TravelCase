package com.cyriltheandroid.travelcase.domain.files.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class File(
    val id: Long = 0,
    val folderId: Long = 0,
    val name: String,
    val fileUri: String,
    @Transient val creationDate: LocalDateTime? = null,
)