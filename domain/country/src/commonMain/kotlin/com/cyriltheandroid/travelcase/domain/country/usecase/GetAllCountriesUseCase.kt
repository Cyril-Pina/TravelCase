package com.cyriltheandroid.travelcase.domain.country.usecase

import com.cyriltheandroid.travelcase.core.model.country.Country

fun interface GetAllCountriesUseCase {
    suspend operator fun invoke(): List<Country>
}

class GetAllCountriesUseCaseImpl : GetAllCountriesUseCase {
    override suspend fun invoke() = Country.toList()
}