package com.cyriltheandroid.travelcase.domain.travel.repository

import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import kotlinx.coroutines.flow.Flow

interface TripsRepository {
    suspend fun getAllTrips(): Flow<List<Trip>>
    suspend fun addTrip(trip: Trip): Long
    suspend fun getTripDetails(tripId: Long): Flow<Trip>
    suspend fun updateTrip(trip: Trip)
    suspend fun deleteTrip(trip: Trip)
}