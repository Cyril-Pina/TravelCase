package com.cyriltheandroid.travelcase.domain.files.di

import com.cyriltheandroid.travelcase.domain.files.usecase.AddFileUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.AddFileUseCaseImpl
import com.cyriltheandroid.travelcase.domain.files.usecase.AddFolderToTripUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.AddFolderToTripUseCaseImpl
import com.cyriltheandroid.travelcase.domain.files.usecase.DeleteFileUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.DeleteFileUseCaseImpl
import com.cyriltheandroid.travelcase.domain.files.usecase.DeleteFolderUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.DeleteFolderUseCaseImpl
import com.cyriltheandroid.travelcase.domain.files.usecase.GetTripsFileCountUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.GetTripsFileCountUseCaseImpl
import com.cyriltheandroid.travelcase.domain.files.usecase.GetFolderDetailsUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.GetFolderDetailsUseCaseImpl
import com.cyriltheandroid.travelcase.domain.files.usecase.GetFoldersFromTripIdUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.GetFoldersFromTripIdUseCaseImpl
import com.cyriltheandroid.travelcase.domain.files.usecase.UpdateFolderDetailsUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.UpdateFolderDetailsUseCaseImpl
import org.koin.dsl.module

val filesDomainModule = module {
    single<GetFoldersFromTripIdUseCase> { GetFoldersFromTripIdUseCaseImpl(get()) }
    single<AddFolderToTripUseCase> { AddFolderToTripUseCaseImpl(get()) }
    single<GetFolderDetailsUseCase> { GetFolderDetailsUseCaseImpl(get()) }
    single<UpdateFolderDetailsUseCase> { UpdateFolderDetailsUseCaseImpl(get()) }
    single<DeleteFileUseCase> { DeleteFileUseCaseImpl(get()) }
    single<AddFileUseCase> { AddFileUseCaseImpl(get()) }
    single<DeleteFolderUseCase> { DeleteFolderUseCaseImpl(get()) }
    single<GetTripsFileCountUseCase> { GetTripsFileCountUseCaseImpl(get()) }
}