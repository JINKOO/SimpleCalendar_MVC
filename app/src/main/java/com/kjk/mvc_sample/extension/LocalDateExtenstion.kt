package com.kjk.mvc_sample.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateFormatAll = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
private val dateFormatMonth = DateTimeFormatter.ofPattern("yyyy년 MM월")

fun LocalDate.formatAll() = dateFormatAll.format(this)
fun LocalDate.formatYearMonth() = dateFormatMonth.format(this)

fun LocalDate.isToday(): Boolean {
    val today = LocalDate.now()
    return today.year == this.year &&
            today.monthValue == this.monthValue &&
            today.dayOfMonth == this.dayOfMonth
}

fun LocalDate.isCurrentMonth(currentMonth: Int): Boolean {
    return monthValue == currentMonth
}
