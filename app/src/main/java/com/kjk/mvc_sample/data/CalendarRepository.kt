package com.kjk.mvc_sample.data

import java.util.*
import kotlin.collections.ArrayList

/**
 *   달력에서 날짜를 생성하는 부분.
 *   Model에서 비즈니스 로직에 해당하는 부분이다. usecase & cache
 */
class CalendarRepository {

    private var calendarItemLists: ArrayList<CalendarItemEntity> = ArrayList()

    /** 달력 data생성 */
    fun fetchCalendar(year: Int, month: Int) {
        val calendar = GregorianCalendar(year, month, 1)

        // 현재 달의 시작 요일
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val startDate = calendar.apply { add(Calendar.DAY_OF_MONTH, -dayOfWeek) }

        repeat(42) {
            (startDate)
                .apply { add(Calendar.DAY_OF_MONTH, 1) }
                .toCalendarItemEntity()
                .also { calendarItemLists.add(it) }
        }
    }

    fun deleteAllDate() {
        calendarItemLists.clear()
    }

    fun getCalendarItemLists(): ArrayList<CalendarItemEntity> {
        return this.calendarItemLists
    }

}