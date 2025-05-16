package com.cyriltheandroid.travelcase.domain.travel.usecase

import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.model.TripStep
import com.cyriltheandroid.travelcase.domain.travel.repository.TripsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

fun interface GetTripDetailsUseCase {
    suspend operator fun invoke(tripId: Long): Flow<Trip>
}

class GetTripDetailsUseCaseImpl(
    private val repository: TripsRepository,
    private val getTripStepUseCase: GetTripStepUseCase,
) : GetTripDetailsUseCase {
    override suspend fun invoke(tripId: Long): Flow<Trip> {
        return repository.getTripDetails(tripId).map { trip ->
            val tripStep = getTripStepFromDates(trip.departureDate, trip.returnDate)
            trip.copy(tripStep = tripStep)
        }
    }

    private suspend fun getTripStepFromDates(
        departureDate: LocalDate?,
        returnDate: LocalDate?
    ): TripStep? {
        if (departureDate == null || returnDate == null) {
            return null
        }

        return getTripStepUseCase(departureDate, returnDate)
    }
}