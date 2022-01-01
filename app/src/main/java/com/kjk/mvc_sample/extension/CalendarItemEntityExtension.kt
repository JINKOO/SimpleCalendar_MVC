package com.kjk.mvc_sample.extension

import com.kjk.mvc_sample.data.CalendarItemEntity
import java.time.LocalDate

fun CalendarItemEntity.toLocalDate(): LocalDate {
    return LocalDate.of(year, month, date)
}