package com.cyriltheandroid.travelcase.data.travel.datasource.db

import com.cyriltheandroid.core.database.db.TravelCaseDatabase
import com.cyriltheandroid.core.database.entity.TripEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class TripsDBDataSourceImpl(
    private val database: TravelCaseDatabase,
) : TripsDBDataSource {
    override suspend fun getAllTrips(): Flow<List<TripEntity>> {
        return database.getTripsDao().getAllTrips()
    }

    override suspend fun insertTrip(trip: TripEntity): Long {
        return database.getTripsDao().insertTrip(trip)
    }

    override suspend fun getTripDetails(tripId: Long): Flow<TripEntity> {
        return database.getTripsDao().getTripDetails(tripId).mapNotNull { it }
    }

    override suspend fun updateTrip(trip: TripEntity) {
        database.getTripsDao().updateTrip(trip)
    }

    override suspend fun deleteTrip(tripEntity: TripEntity) {
        database.getTripsDao().deleteTrip(tripEntity)
    }
}