package com.cyriltheandroid.travelcase.data.travel.datasource.db

import com.cyriltheandroid.core.database.entity.TripEntity
import kotlinx.coroutines.flow.Flow

interface TripsDBDataSource {
    suspend fun getAllTrips(): Flow<List<TripEntity>>
    suspend fun insertTrip(trip: TripEntity): Long
    suspend fun getTripDetails(tripId: Long): Flow<TripEntity>
    suspend fun updateTrip(trip: TripEntity)
    suspend fun deleteTrip(tripEntity: TripEntity)
}