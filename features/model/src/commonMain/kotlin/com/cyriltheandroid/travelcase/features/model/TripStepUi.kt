package com.cyriltheandroid.travelcase.features.model

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("TripStepUi", exact = true)
sealed class TripStepUi {
    data class Incoming(val daysLeft: Int) : TripStepUi()
    data object Pending : TripStepUi()
    data object Finished : TripStepUi()
}