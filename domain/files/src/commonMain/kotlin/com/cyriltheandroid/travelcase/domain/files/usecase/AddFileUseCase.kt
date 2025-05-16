package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.File
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository

fun interface AddFileUseCase {
    suspend operator fun invoke(file: File)
}

class AddFileUseCaseImpl(
    private val repository: FilesRepository,
) : AddFileUseCase {
    override suspend fun invoke(file: File) {
        repository.addFile(file)
    }
}