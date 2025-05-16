package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository

fun interface DeleteFolderUseCase {
    suspend operator fun invoke(folder: Folder)
}

class DeleteFolderUseCaseImpl(
    private val repository: FilesRepository,
) : DeleteFolderUseCase {
    override suspend fun invoke(folder: Folder) {
        repository.deleteFolder(folder)
    }
}