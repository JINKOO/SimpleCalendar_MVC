package com.kjk.mvc_sample.extension

import com.kjk.mvc_sample.data.CalendarItemEntity
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

const val YYYY_MM_dd = "yyyy년 MM월 dd일"
const val YYYY_MM = "yyyy년 MM월"

private val formatAllDate = DateTimeFormatter.ofPattern(YYYY_MM_dd)
private val formatSimple = DateTimeFormatter.ofPattern(YYYY_MM)

fun LocalDate.formatYearMonth(): String {
    return format(formatSimple)
}

fun LocalDate.formatAll(): String {
    return format(formatAllDate)
}

fun LocalDate.isCurrentMonth(month: Int): Boolean {
    return monthValue == month
}

fun LocalDate.toCalendarItemEntity(): CalendarItemEntity {
    return CalendarItemEntity(
            year,
            monthValue,
            dayOfMonth
    )
}