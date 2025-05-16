package com.cyriltheandroid.travelcase.data.files.datasource.db

import com.cyriltheandroid.core.database.db.TravelCaseDatabase
import com.cyriltheandroid.core.database.entity.FileEntity
import com.cyriltheandroid.core.database.entity.FolderEntity
import com.cyriltheandroid.core.database.entity.FolderWithFilesEntity
import com.cyriltheandroid.core.database.entity.TripFileCountEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class FilesDBDataSourceImpl(
    private val database: TravelCaseDatabase,
) : FilesDBDataSource {

    override fun getAllFoldersFromTrip(tripId: Long): Flow<List<FolderWithFilesEntity>> {
        return database.getTripsDao().getFoldersByTrip(tripId)
    }

    override suspend fun addFolderToTrip(folderWithFilesEntity: FolderWithFilesEntity) {
        val folderId = database.getTripsDao().insertFolder(folderWithFilesEntity.folderEntity)
        folderWithFilesEntity.fileEntities
            .map {
                val fileEntityWithFolderId = it.copy(folderId = folderId)
                database.getTripsDao().insertFile(fileEntityWithFolderId)
            }
    }

    override fun getFolderDetails(folderId: Long): Flow<FolderWithFilesEntity> {
        return database.getTripsDao().getFolderDetails(folderId).mapNotNull { it }
    }

    override suspend fun updateFolder(folderEntity: FolderEntity) {
        database.getTripsDao().updateFolder(folderEntity)
    }

    override suspend fun deleteFolder(folderEntity: FolderEntity) {
        database.getTripsDao().deleteFolder(folderEntity)
    }

    override suspend fun deleteFile(fileEntity: FileEntity) {
        database.getTripsDao().deleteFile(fileEntity)
    }

    override suspend fun addFile(fileEntity: FileEntity) {
        database.getTripsDao().insertFile(fileEntity)
    }

    override fun getFileCountGroupedByTrip(): Flow<List<TripFileCountEntity>> {
        return database.getTripsDao().getFileCountGroupedByTrip()
    }
}