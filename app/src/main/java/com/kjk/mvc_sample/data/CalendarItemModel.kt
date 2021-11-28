package com.kjk.mvc_sample.data

/**
 *   달력
 *   비즈니스 로직에 해당하는 부분이다.
 */
class CalendarItemModel {

    private var calendarItemLists: ArrayList<CalendarItemEntity> = ArrayList()

    fun createCalendarItem() {
        for (date in 0 until 100) {
            val calendarItemEntity = CalendarItemEntity(date + 1)
            calendarItemLists.add(calendarItemEntity)
        }
    }

    fun getCalendarItemLists() = this.calendarItemLists
}