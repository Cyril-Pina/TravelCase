package com.cyriltheandroid.travelcase.data.files.datasource.db

import com.cyriltheandroid.core.database.entity.FileEntity
import com.cyriltheandroid.core.database.entity.FolderEntity
import com.cyriltheandroid.core.database.entity.FolderWithFilesEntity
import com.cyriltheandroid.core.database.entity.TripFileCountEntity
import kotlinx.coroutines.flow.Flow

interface FilesDBDataSource {
    fun getAllFoldersFromTrip(tripId: Long): Flow<List<FolderWithFilesEntity>>
    suspend fun addFolderToTrip(folderWithFilesEntity: FolderWithFilesEntity)
    fun getFolderDetails(folderId: Long): Flow<FolderWithFilesEntity>
    suspend fun updateFolder(folderEntity: FolderEntity)
    suspend fun deleteFolder(folderEntity: FolderEntity)
    suspend fun deleteFile(fileEntity: FileEntity)
    suspend fun addFile(fileEntity: FileEntity)
    fun getFileCountGroupedByTrip(): Flow<List<TripFileCountEntity>>
}