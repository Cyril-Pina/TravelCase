package com.cyriltheandroid.travelcase.domain.travel.usecase

import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.repository.TripsRepository

fun interface UpdateTripDetailsUseCase {
    suspend operator fun invoke(trip: Trip)
}

class UpdateTripDetailsUseCaseImpl(
    private val repository: TripsRepository,
) : UpdateTripDetailsUseCase {
    override suspend fun invoke(trip: Trip) {
        repository.updateTrip(trip)
    }
}