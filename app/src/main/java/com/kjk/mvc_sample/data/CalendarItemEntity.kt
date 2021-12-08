package com.kjk.mvc_sample.data

import java.util.*

/**
 *  달력 Entity Class.
 *  Model에서 Entity에 해당한다.
 *  우선, 날짜 data만을 가진다.
 */
data class CalendarItemEntity(
    val year: Int,
    val month: Int,
    val date: Int   // 날짜
) {
    override fun toString(): String {
        return "$year 년 $month 월 $date 일"
    }

    fun getDayOfWeek(): Int {
        return toCalendar().get(Calendar.DAY_OF_WEEK)
    }
}

fun Calendar.toCalendarItemEntity(): CalendarItemEntity {
    return CalendarItemEntity(
        get(Calendar.YEAR),
        get(Calendar.MONTH),
        get(Calendar.DAY_OF_MONTH)
    )
}

fun CalendarItemEntity.toCalendar(): Calendar {
    return GregorianCalendar(year, month, date)
}