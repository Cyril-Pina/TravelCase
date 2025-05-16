package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository

fun interface UpdateFolderDetailsUseCase {
    suspend operator fun invoke(folder: Folder)
}

class UpdateFolderDetailsUseCaseImpl(
    private val repository: FilesRepository,
) : UpdateFolderDetailsUseCase {
    override suspend fun invoke(folder: Folder) {
        repository.updateFolder(folder)
    }
}