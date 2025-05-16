package com.cyriltheandroid.travelcase.data.files.di

import com.cyriltheandroid.travelcase.data.files.datasource.db.FilesDBDataSource
import com.cyriltheandroid.travelcase.data.files.datasource.db.FilesDBDataSourceImpl
import com.cyriltheandroid.travelcase.data.files.repository.FilesRepositoryImpl
import com.cyriltheandroid.travelcase.domain.files.repository.FilesRepository
import org.koin.dsl.module

val filesDataModule = module {
    single<FilesDBDataSource> { FilesDBDataSourceImpl(get()) }

    single<FilesRepository> { FilesRepositoryImpl(get()) }
}