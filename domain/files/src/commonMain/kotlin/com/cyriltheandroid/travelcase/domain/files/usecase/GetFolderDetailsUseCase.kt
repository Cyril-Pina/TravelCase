package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository
import kotlinx.coroutines.flow.Flow

fun interface GetFolderDetailsUseCase {
    suspend operator fun invoke(folderId: Long): Flow<Folder>
}

class GetFolderDetailsUseCaseImpl(
    private val repository: FilesRepository,
) : GetFolderDetailsUseCase {
    override suspend fun invoke(folderId: Long): Flow<Folder> {
        return repository.getFolderDetails(folderId)
    }
}
