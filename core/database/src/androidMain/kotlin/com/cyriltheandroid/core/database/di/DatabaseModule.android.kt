package com.cyriltheandroid.core.database.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.cyriltheandroid.core.database.db.TravelCaseDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

fun createRoomDatabase(ctx: Context): TravelCaseDatabase {
    val dbFile = ctx.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<TravelCaseDatabase>(ctx, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .fallbackToDestructiveMigration(false)
        .build()
}

actual val databaseModule = module {
    single<TravelCaseDatabase> { createRoomDatabase(get()) }
}