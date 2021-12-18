package com.kjk.mvc_sample.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatAll: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
private val formatSimple: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월")

fun LocalDate.formatAllDate(): String {
    return format(formatAll)
}

fun LocalDate.formatYearMonth(): String {
    return format(formatSimple)
}