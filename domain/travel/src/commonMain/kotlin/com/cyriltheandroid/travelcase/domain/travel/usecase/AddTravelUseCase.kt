package com.cyriltheandroid.travelcase.domain.travel.usecase

import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.repository.TripsRepository

fun interface AddTravelUseCase {
    suspend operator fun invoke(trip: Trip): Long
}

class AddTravelUseCaseImpl(
    private val repository: TripsRepository,
) : AddTravelUseCase {
    override suspend fun invoke(trip: Trip): Long = repository.addTrip(trip)
}