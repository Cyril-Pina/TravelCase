package com.cyriltheandroid.travelcase.data.travel.repository

import com.cyriltheandroid.travelcase.data.travel.datasource.db.TripsDBDataSource
import com.cyriltheandroid.travelcase.data.travel.mapper.toTrip
import com.cyriltheandroid.travelcase.data.travel.mapper.toTripEntity
import com.cyriltheandroid.travelcase.domain.travel.model.Trip
import com.cyriltheandroid.travelcase.domain.travel.repository.TripsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TripsRepositoryImpl(
    private val dbDataSource: TripsDBDataSource,
) : TripsRepository {
    override suspend fun getAllTrips(): Flow<List<Trip>> {
        return dbDataSource.getAllTrips().map { trips ->
            trips.map { tripEntity ->
                tripEntity.toTrip()
            }
        }
    }

    override suspend fun addTrip(trip: Trip): Long {
        return dbDataSource.insertTrip(trip.toTripEntity())
    }

    override suspend fun getTripDetails(tripId: Long): Flow<Trip> {
        return dbDataSource.getTripDetails(tripId = tripId).map { it.toTrip() }
    }

    override suspend fun updateTrip(trip: Trip) {
        dbDataSource.updateTrip(trip.toTripEntity())
    }

    override suspend fun deleteTrip(trip: Trip) {
        dbDataSource.deleteTrip(trip.toTripEntity())
    }
}
