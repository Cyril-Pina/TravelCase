package com.cyriltheandroid.core.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FolderWithFilesEntity(
    @Embedded
    val folderEntity: FolderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "folderId"
    )
    val fileEntities: List<FileEntity>
)