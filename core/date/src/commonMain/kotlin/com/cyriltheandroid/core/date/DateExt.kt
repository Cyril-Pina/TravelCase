package com.cyriltheandroid.core.date

import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.raedghazal.kotlinx_datetime_ext.atStartOfDay
import com.raedghazal.kotlinx_datetime_ext.durationUntil
import com.raedghazal.kotlinx_datetime_ext.minus
import com.raedghazal.kotlinx_datetime_ext.now
import com.raedghazal.kotlinx_datetime_ext.plus
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

/**
 * Use [DateFormat] for format
 */
fun String.toLocalDateTime(format: String): LocalDateTime {
    val formatter = LocalDateTimeFormatter.ofPattern(format, Locale.default())
    return formatter.parseToLocalDateTime(this)
}

/**
 * Use [DateFormat] for format
 */
fun String.toLocalDate(format: String): LocalDate {
    return toLocalDateTime(format).date
}

/**
 * Use [DateFormat] for format
 */
fun LocalDateTime.toString(format: String): String {
    val formatter = LocalDateTimeFormatter.ofPattern(format, Locale.default())
    return formatter.format(this)
}

/**
 * Use [DateFormat] for format
 */
fun LocalDateTime.Companion.nowToString(format: String): String {
    return this.now().toString(format)
}

/**
 * Use [DateFormat] for format
 */
fun LocalDate.Companion.nowToString(format: String): String {
    return this.now().toString(format)
}

/**
 * Use [DateFormat] for format
 */
fun LocalDate.toString(format: String): String {
    val formatter = LocalDateTimeFormatter.ofPattern(format, Locale.default())
    return formatter.format(this)
}

fun LocalDateTime.greaterThan(localDateTime: LocalDateTime): Boolean {
    return this > localDateTime
}

fun LocalDateTime.lessThan(localDateTime: LocalDateTime): Boolean {
    return this < localDateTime
}

fun LocalDateTime.equalsTo(localDateTime: LocalDateTime): Boolean {
    return this == localDateTime
}

fun LocalDate.greaterThan(localDate: LocalDate): Boolean {
    return this > localDate
}

fun LocalDate.lessThan(localDate: LocalDate): Boolean {
    return this < localDate
}

fun LocalDate.isBetween(firstLocalDate: LocalDate, secondLocalDate: LocalDate): Boolean {
    return this.equalsTo(firstLocalDate) || this.equalsTo(secondLocalDate) ||
            (this.greaterThan(firstLocalDate) && this.lessThan(secondLocalDate))
}

fun LocalDate.equalsTo(localDate: LocalDate): Boolean {
    return this == localDate
}

fun LocalDateTime.toLocalDate(): LocalDate {
    return this.date
}

fun LocalDate.toLocalDateTime(): LocalDateTime {
    return this.atStartOfDay()
}

fun LocalDateTime.minus(value: Int, unit: DurationUnit): LocalDateTime {
    return when (unit) {
        DurationUnit.NANOSECONDS -> this - value.nanoseconds
        DurationUnit.MICROSECONDS -> this - value.microseconds
        DurationUnit.MILLISECONDS -> this - value.milliseconds
        DurationUnit.SECONDS -> this - value.seconds
        DurationUnit.MINUTES -> this - value.minutes
        DurationUnit.HOURS -> this - value.hours
        DurationUnit.DAYS -> this - value.days
        else -> this
    }
}

fun LocalDate.minus(value: Int, unit: DurationUnit): LocalDate {
    return this.toLocalDateTime().minus(value, unit).toLocalDate()
}

fun LocalDateTime.durationUntil(localDateTime: LocalDateTime): Duration {
    return this durationUntil localDateTime
}

fun LocalDate.durationUntil(localDate: LocalDate): Duration {
    return this.toLocalDateTime().durationUntil(localDate.toLocalDateTime())
}

fun LocalDateTime.plus(value: Int, unit: DurationUnit): LocalDateTime {
    return when (unit) {
        DurationUnit.NANOSECONDS -> this + value.nanoseconds
        DurationUnit.MICROSECONDS -> this + value.microseconds
        DurationUnit.MILLISECONDS -> this + value.milliseconds
        DurationUnit.SECONDS -> this + value.seconds
        DurationUnit.MINUTES -> this + value.minutes
        DurationUnit.HOURS -> this + value.hours
        DurationUnit.DAYS -> this + value.days
        else -> this
    }
}

fun LocalDate.plus(value: Int, unit: DurationUnit): LocalDate {
    return this.toLocalDateTime().plus(value, unit).toLocalDate()
}

fun Long?.toLocalDate(): LocalDate? {
    if (this == null) {
        return null
    }
    val epochDays = this / 1000 / 60 / 60 / 24
    return LocalDate.fromEpochDays(epochDays.toInt())
}

fun LocalDate.toMillis(): Long {
    val epochDays = this.toEpochDays().toLong()
    return epochDays * 24 * 60 * 60 * 1000
}