package com.cyriltheandroid.travelcase.domain.files.repository

import com.cyriltheandroid.travelcase.domain.files.model.File
import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.model.TripFileCount
import kotlinx.coroutines.flow.Flow

interface FilesRepository {
    suspend fun getFoldersFromTrip(tripId: Long): Flow<List<Folder>>
    suspend fun addFolderToTrip(folder: Folder)
    suspend fun getFolderDetails(folderId: Long): Flow<Folder>
    suspend fun updateFolder(folder: Folder)
    suspend fun deleteFolder(folder: Folder)
    suspend fun deleteFile(file: File)
    suspend fun addFile(file: File)
    suspend fun getFileCountGroupedByTrip(): Flow<List<TripFileCount>>
}