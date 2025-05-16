package com.cyriltheandroid.travelcase.domain.travel.usecase

import com.cyriltheandroid.core.date.durationUntil
import com.cyriltheandroid.core.date.greaterThan
import com.cyriltheandroid.core.date.isBetween
import com.cyriltheandroid.travelcase.domain.travel.model.TripStep
import com.raedghazal.kotlinx_datetime_ext.now
import kotlinx.datetime.LocalDate

private const val IncomingTripLimitDays = 7

fun interface GetTripStepUseCase {
    suspend operator fun invoke(departureDate: LocalDate, returnDate: LocalDate): TripStep?
}

class GetTripStepUseCaseImpl : GetTripStepUseCase {

    override suspend fun invoke(departureDate: LocalDate, returnDate: LocalDate): TripStep? {
        val today = LocalDate.now()
        val daysTodayToDeparture = today.durationUntil(departureDate).inWholeDays.toInt()
        return when {
            daysTodayToDeparture in 1..IncomingTripLimitDays -> {
                TripStep.Incoming(daysLeft = daysTodayToDeparture)
            }

            today.isBetween(departureDate, returnDate) -> {
                TripStep.Pending
            }

            today.greaterThan(returnDate) -> {
                TripStep.Finished
            }

            else -> null
        }
    }
}