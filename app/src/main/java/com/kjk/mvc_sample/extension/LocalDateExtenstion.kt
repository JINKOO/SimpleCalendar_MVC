package com.kjk.mvc_sample.extension

import java.time.LocalDate



fun LocalDate.isToday(): Boolean {
    val today = LocalDate.now()
    return today.year == this.year &&
            today.monthValue == this.monthValue &&
            today.dayOfMonth == this.dayOfMonth
}

fun LocalDate.isCurrentMonth(currentMonth: Int): Boolean {
    return monthValue == currentMonth
}
