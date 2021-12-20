package com.kjk.mvc_sample.extension

import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

private val formatAllDate = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
private val formatSimple = DateTimeFormatter.ofPattern("yyyy년 MM월")

fun LocalDate.formatYearMonth(): String {
    return format(formatSimple)
}

fun LocalDate.formatAll(): String {
    return format(formatAllDate)
}

fun LocalDate.isCurrentMonth(month: Int): Boolean {
    return monthValue == month
}