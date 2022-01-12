package com.kjk.mvc_sample.data

import java.time.LocalDate

interface CalendarDataSender {
    fun getBaseDate(): LocalDate
    fun isCurrentMonth(item: CalendarItemEntity, currentMonth: Int): Boolean
    fun isToday(item: CalendarItemEntity): Boolean
    fun isSaturday(item: CalendarItemEntity): Boolean
    fun isSunday(item: CalendarItemEntity): Boolean
    fun getItemList(): ArrayList<CalendarItemEntity>
    fun getSelectedDate(position: Int): LocalDate
}