package com.kjk.mvc_sample.data

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

/**
 *   날짜를 생성하는 부분
 *   비즈니스 로직에 해당하는 부분이다.
 */
class CalendarItemModel {

    private var calendarItemLists: ArrayList<CalendarItemEntity> = ArrayList()


    fun createCalendarDate(year: Int, month: Int) {
        val calendar = GregorianCalendar(year, month, 1)

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        Log.w("1111", "${dayOfWeek}, ${max}")

        for (date in 1 .. max) {
            val calendarDateItem = CalendarItemEntity(date)
            calendarItemLists.add(calendarDateItem)
        }
    }

    fun getCalendarItemLists() = this.calendarItemLists
}