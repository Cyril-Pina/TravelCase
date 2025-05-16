package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.File
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository

fun interface DeleteFileUseCase {
    suspend operator fun invoke(file: File)
}

class DeleteFileUseCaseImpl(
    private val repository: FilesRepository,
) : DeleteFileUseCase {
    override suspend fun invoke(file: File) {
        repository.deleteFile(file)
    }
}