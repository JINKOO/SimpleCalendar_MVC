package com.kjk.mvc_sample.data

/**
 *   달력
 *   비즈니스 로직에 해당하는 부분이다.
 */
class CalendarItemModel {

    private var calendarItemLists: ArrayList<CalendarItemEntity> = ArrayList()

    fun createCalendarItem() {

    }

    fun getCalendarItemLists() = this.calendarItemLists
}