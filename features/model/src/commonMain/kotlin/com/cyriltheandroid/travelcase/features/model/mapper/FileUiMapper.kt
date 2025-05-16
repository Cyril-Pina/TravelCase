package com.cyriltheandroid.travelcase.features.model.mapper

import com.cyriltheandroid.travelcase.domain.files.model.File
import com.cyriltheandroid.travelcase.features.model.FileUi
import com.raedghazal.kotlinx_datetime_ext.now
import kotlinx.datetime.LocalDateTime

fun List<FileUi>.toFiles(folderId: Long = 0) = this.map { fileUi -> fileUi.toFile(folderId) }

fun FileUi.toFile(folderId: Long) = File(
    id = this.id,
    folderId = folderId,
    name = this.name,
    fileUri = this.fileUri,
    creationDate = LocalDateTime.now(),
)

fun List<File>.toFilesUi() = this.map { file -> file.toFileUi() }

fun File.toFileUi() = FileUi(
    id = this.id,
    name = this.name,
    fileUri = this.fileUri,
)