package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository
import kotlinx.coroutines.flow.Flow

fun interface GetFoldersFromTripIdUseCase {
    suspend operator fun invoke(tripId: Long): Flow<List<Folder>>
}

class GetFoldersFromTripIdUseCaseImpl(
    private val repository: FilesRepository,
) : GetFoldersFromTripIdUseCase {
    override suspend fun invoke(tripId: Long): Flow<List<Folder>> {
        return repository.getFoldersFromTrip(tripId)
    }
}
