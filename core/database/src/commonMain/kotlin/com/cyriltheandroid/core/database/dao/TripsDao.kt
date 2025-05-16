package com.cyriltheandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.cyriltheandroid.core.database.entity.FileEntity
import com.cyriltheandroid.core.database.entity.FolderEntity
import com.cyriltheandroid.core.database.entity.FolderWithFilesEntity
import com.cyriltheandroid.core.database.entity.TripEntity
import com.cyriltheandroid.core.database.entity.TripFileCountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TripsDao {

    @Query("SELECT * FROM trips")
    fun getAllTrips(): Flow<List<TripEntity>>

    @Query("SELECT * FROM trips WHERE id = :tripId LIMIT 1")
    fun getTripDetails(tripId: Long): Flow<TripEntity?>

    @Insert
    suspend fun insertTrip(trip: TripEntity): Long

    @Update
    suspend fun updateTrip(trip: TripEntity)

    @Delete
    suspend fun deleteTrip(trip: TripEntity)

    @Transaction
    @Query("SELECT * FROM folders WHERE tripId = :tripId")
    fun getFoldersByTrip(tripId: Long): Flow<List<FolderWithFilesEntity>>

    @Transaction
    @Query("SELECT * FROM folders WHERE id = :folderId LIMIT 1")
    fun getFolderDetails(folderId: Long): Flow<FolderWithFilesEntity?>

    @Insert
    suspend fun insertFolder(folder: FolderEntity): Long
    @Update
    suspend fun updateFolder(folder: FolderEntity)
    @Delete
    suspend fun deleteFolder(folder: FolderEntity)

    @Query("""
        SELECT fo.tripId, COUNT(f.id) as fileCount
        FROM files f
        INNER JOIN folders fo ON f.folderId = fo.id
        GROUP BY fo.tripId
    """)
    fun getFileCountGroupedByTrip(): Flow<List<TripFileCountEntity>>

    @Insert
    suspend fun insertFile(file: FileEntity): Long
    @Delete
    suspend fun deleteFile(file: FileEntity)
}