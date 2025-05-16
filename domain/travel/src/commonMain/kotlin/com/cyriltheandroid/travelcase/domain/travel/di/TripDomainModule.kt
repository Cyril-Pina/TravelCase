package com.cyriltheandroid.travelcase.domain.travel.di

import com.cyriltheandroid.travelcase.domain.travel.usecase.AddTravelUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.AddTravelUseCaseImpl
import com.cyriltheandroid.travelcase.domain.travel.usecase.DeleteTripUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.DeleteTripUseCaseImpl
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetAllTripsUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetAllTripsUseCaseImpl
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetTripDetailsUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetTripDetailsUseCaseImpl
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetTripStepUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.GetTripStepUseCaseImpl
import com.cyriltheandroid.travelcase.domain.travel.usecase.UpdateTripDetailsUseCase
import com.cyriltheandroid.travelcase.domain.travel.usecase.UpdateTripDetailsUseCaseImpl
import org.koin.dsl.module

val tripDomainModule = module {
    single<GetAllTripsUseCase> { GetAllTripsUseCaseImpl(get(), get()) }
    single<AddTravelUseCase> { AddTravelUseCaseImpl(get()) }
    single<GetTripStepUseCase> { GetTripStepUseCaseImpl() }
    single<GetTripDetailsUseCase> { GetTripDetailsUseCaseImpl(get(), get()) }
    single<UpdateTripDetailsUseCase> { UpdateTripDetailsUseCaseImpl(get()) }
    single<DeleteTripUseCase> { DeleteTripUseCaseImpl(get()) }
}