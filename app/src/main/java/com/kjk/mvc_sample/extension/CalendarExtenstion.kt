package com.kjk.mvc_sample.extension

import java.time.LocalDate
import java.util.*

fun LocalDate.isToday(): Boolean {
    val today = LocalDate.now()
    return today.year == this.year &&
            today.monthValue == this.monthValue &&
            today.dayOfMonth == this.dayOfMonth
}
