package com.cyriltheandroid.travelcase.domain.files.usecase

import com.cyriltheandroid.travelcase.domain.files.model.TripFileCount
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository
import kotlinx.coroutines.flow.Flow

fun interface GetTripsFileCountUseCase {
    suspend operator fun invoke(): Flow<List<TripFileCount>>
}

class GetTripsFileCountUseCaseImpl(
    private val repository: FilesRepository,
) : GetTripsFileCountUseCase {

    override suspend fun invoke(): Flow<List<TripFileCount>> {
        return repository.getFileCountGroupedByTrip()
    }
}
