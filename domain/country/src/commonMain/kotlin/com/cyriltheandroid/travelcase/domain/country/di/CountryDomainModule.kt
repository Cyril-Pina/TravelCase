package com.cyriltheandroid.travelcase.domain.country.di

import com.cyriltheandroid.travelcase.domain.country.usecase.GetAllCountriesUseCase
import com.cyriltheandroid.travelcase.domain.country.usecase.GetAllCountriesUseCaseImpl
import org.koin.dsl.module

val countryDomainModule = module {
    single<GetAllCountriesUseCase> { GetAllCountriesUseCaseImpl() }
}