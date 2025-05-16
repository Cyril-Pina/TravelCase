package com.cyriltheandroid.travelcase.domain.travel.usecase

import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.model.TripStep
import com.cyriltheandroid.travelcase.domain.travel.repository.TripsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

fun interface GetAllTripsUseCase {
    suspend operator fun invoke(): Flow<List<Trip>>
}

class GetAllTripsUseCaseImpl(
    private val repository: TripsRepository,
    private val getTripStepUseCase: GetTripStepUseCase,
) : GetAllTripsUseCase {
    override suspend fun invoke() = repository.getAllTrips().map { trips ->
        trips.map { trip ->
            val tripStep = getTripStepFromDates(trip.departureDate, trip.returnDate)
            trip.copy(tripStep = tripStep)
        }.sortedByDescending { trip -> trip.creationDate }
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