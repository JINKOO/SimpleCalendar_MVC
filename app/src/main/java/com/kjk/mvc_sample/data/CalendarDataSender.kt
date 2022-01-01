package com.kjk.mvc_sample.data

import java.time.LocalDate

interface CalendarDataSender {
    fun getLocalDateInstance(): LocalDate
    fun getBaseDate(): LocalDate
    fun isToday(item: CalendarItemEntity): Boolean
    fun isSaturday(item: CalendarItemEntity): Boolean
    fun isSunday(item: CalendarItemEntity): Boolean
    fun getItemList(): ArrayList<CalendarItemEntity>
}