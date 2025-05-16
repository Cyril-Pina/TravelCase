package com.cyriltheandroid.travelcase.data.travel.di

import com.cyriltheandroid.travelcase.data.travel.datasource.db.TripsDBDataSource
import com.cyriltheandroid.travelcase.data.travel.datasource.db.TripsDBDataSourceImpl
import com.cyriltheandroid.travelcase.data.travel.repository.TripsRepositoryImpl
import com.cyriltheandroid.travelcase.domain.travel.repository.TripsRepository
import org.koin.dsl.module

val tripDataModule = module {
    single<TripsDBDataSource> { TripsDBDataSourceImpl(get()) }

    single<TripsRepository> { TripsRepositoryImpl(get()) }
}