package com.cyriltheandroid.travelcase.data.files.mapper

import com.cyriltheandroid.core.database.entity.FileEntity
import com.cyriltheandroid.core.database.entity.FolderEntity
import com.cyriltheandroid.core.database.entity.FolderWithFilesEntity
import com.cyriltheandroid.core.database.entity.TripFileCountEntity
import com.cyriltheandroid.core.date.DateFormat
import com.cyriltheandroid.core.date.toLocalDateTime
import com.cyriltheandroid.core.date.toString
import com.cyriltheandroid.travelcase.domain.files.model.File
import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.domain.files.model.TripFileCount

fun FolderWithFilesEntity.toFolder() = Folder(
    id = this.folderEntity.id,
    tripId = this.folderEntity.tripId,
    title = this.folderEntity.title,
    type = FolderType.valueOf(this.folderEntity.type),
    files = this.fileEntities.toFiles(),
    creationDate = this.folderEntity.creationDate.toLocalDateTime(format = DateFormat.SqlFormat)
)

private fun List<FileEntity>.toFiles() = this.map { fileEntity ->
    File(
        id = fileEntity.id,
        folderId = fileEntity.folderId,
        name = fileEntity.name,
        fileUri = fileEntity.localPath,
        creationDate = fileEntity.creationDate.toLocalDateTime(format = DateFormat.SqlFormat),
    )
}

fun Folder.toFolderWithFilesEntity() = FolderWithFilesEntity(
    folderEntity = this.toFolderEntity(),
    fileEntities = this.toFileEntities(),
)

fun Folder.toFolderEntity() = FolderEntity(
    id = this.id,
    tripId = this.tripId,
    title = this.title,
    type = this.type.name,
    creationDate = this.creationDate.toString(format = DateFormat.SqlFormat),
)

fun Folder.toFileEntities() = this.files.map { file ->
    FileEntity(
        id = file.id,
        folderId = file.folderId,
        name = file.name,
        localPath = file.fileUri,
        mimeType = "",
        creationDate = file.creationDate?.toString(format = DateFormat.SqlFormat).orEmpty(),
    )
}

fun File.toFileEntity() = FileEntity(
    id = this.id,
    folderId = this.folderId,
    name = this.name,
    localPath = this.fileUri,
    mimeType = "",
    creationDate = this.creationDate?.toString(format = DateFormat.SqlFormat).orEmpty(),
)

fun TripFileCountEntity.toFileCount() = TripFileCount(
    tripId = this.tripId,
    fileCount = this.fileCount,
)