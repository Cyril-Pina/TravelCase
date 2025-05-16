package com.cyriltheandroid.travelcase.features.filepreview.model

import com.cyriltheandroid.travelcase.features.model.FileUi
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * @ObjCName is used to set an exact class name for
 * iOS export. Without this annotation, the class name
 * is prefixed by the module name.
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("FilePreviewUiState", exact = true)
data class FilePreviewUiState(
    val filesUris: List<FileUi> = listOf(),
    val index: Int = 0,
    val showButtonActions: Boolean = false,
)
