package com.cyriltheandroid.travelcase.domain.files.model

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("FolderType", exact = true)
enum class FolderType {
    Ticket,
    Accommodation,
    VehicleRental,
    Other,
}