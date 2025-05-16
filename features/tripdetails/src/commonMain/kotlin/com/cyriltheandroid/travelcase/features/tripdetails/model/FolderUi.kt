package com.cyriltheandroid.travelcase.features.tripdetails.model

import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.features.model.FileUi
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("FolderUi", exact = true)
data class FolderUi(
    val id: Long = 0L,
    val title: String = "",
    val type: FolderType? = null,
    val files: List<FileUi> = listOf(),
)
