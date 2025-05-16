package com.cyriltheandroid.travelcase.features.model

import com.cyriltheandroid.travelcase.domain.files.model.File
import kotlinx.serialization.Serializable
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("FilePreviewData", exact = true)
@Serializable
data class FilePreviewData(
    val files: List<File> = listOf(),
    val initialIndex: Int = 0,
    val folderId: Long = 0L,
    val showButtonActions: Boolean = true,
)
