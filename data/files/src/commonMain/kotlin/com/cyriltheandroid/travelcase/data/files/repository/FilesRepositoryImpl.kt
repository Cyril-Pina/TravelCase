package com.cyriltheandroid.travelcase.data.files.repository

import com.cyriltheandroid.travelcase.data.files.datasource.db.FilesDBDataSource
import com.cyriltheandroid.travelcase.data.files.mapper.toFileCount
import com.cyriltheandroid.travelcase.data.files.mapper.toFileEntity
import com.cyriltheandroid.travelcase.data.files.mapper.toFolder
import com.cyriltheandroid.travelcase.data.files.mapper.toFolderEntity
import com.cyriltheandroid.travelcase.data.files.mapper.toFolderWithFilesEntity
import com.cyriltheandroid.travelcase.domain.files.model.File
import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.model.TripFileCount
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilesRepositoryImpl(
    private val filesDBDataSource: FilesDBDataSource,
) : FilesRepository {

    override suspend fun getFoldersFromTrip(tripId: Long): Flow<List<Folder>> {
        return filesDBDataSource.getAllFoldersFromTrip(tripId)
            .map { it.map { folderEntity -> folderEntity.toFolder() } }
    }

    override suspend fun addFolderToTrip(folder: Folder) {
        filesDBDataSource.addFolderToTrip(folder.toFolderWithFilesEntity())
    }

    override suspend fun getFolderDetails(folderId: Long): Flow<Folder> {
        return filesDBDataSource.getFolderDetails(folderId).map { it.toFolder() }
    }

    override suspend fun updateFolder(folder: Folder) {
        filesDBDataSource.updateFolder(folder.toFolderEntity())
    }

    override suspend fun deleteFolder(folder: Folder) {
        filesDBDataSource.deleteFolder(folder.toFolderEntity())
    }

    override suspend fun deleteFile(file: File) {
        filesDBDataSource.deleteFile(file.toFileEntity())
    }

    override suspend fun addFile(file: File) {
        filesDBDataSource.addFile(file.toFileEntity())
    }

    override suspend fun getFileCountGroupedByTrip(): Flow<List<TripFileCount>> {
        return filesDBDataSource.getFileCountGroupedByTrip().map { tripFileEntities ->
            tripFileEntities.map { it.toFileCount() }
        }
    }
}