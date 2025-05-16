package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository

fun interface AddFolderToTripUseCase {
    suspend operator fun invoke(folder: Folder)
}

class AddFolderToTripUseCaseImpl(
    private val repository: FilesRepository,
) : AddFolderToTripUseCase {
    override suspend fun invoke(folder: Folder) {
        repository.addFolderToTrip(folder)
    }
}