package com.cyriltheandroid.core.database.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.cyriltheandroid.core.database.dao.TripsDao
import com.cyriltheandroid.core.database.entity.FileEntity
import com.cyriltheandroid.core.database.entity.FolderEntity
import com.cyriltheandroid.core.database.entity.TripEntity

@Database(
    entities = [
        TripEntity::class,
        FolderEntity::class,
        FileEntity::class,
    ],
    version = 10
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class TravelCaseDatabase : RoomDatabase() {
    abstract fun getTripsDao(): TripsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<TravelCaseDatabase> {
    override fun initialize(): TravelCaseDatabase
}