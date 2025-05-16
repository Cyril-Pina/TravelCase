package com.cyriltheandroid.travelcase.domain.travel.model

sealed interface TripStep {
    data class Incoming(val daysLeft: Int) : TripStep
    data object Pending : TripStep
    data object Finished : TripStep
}