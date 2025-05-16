package com.cyriltheandroid.travelcase.domain.travel.usecase

import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.repository.TripsRepository

fun interface DeleteTripUseCase {
    suspend operator fun invoke(trip: Trip)
}

class DeleteTripUseCaseImpl(
    private val repository: TripsRepository,
) : DeleteTripUseCase {
    override suspend fun invoke(trip: Trip) {
        repository.deleteTrip(trip)
    }
}