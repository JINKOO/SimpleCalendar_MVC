package com.kjk.mvc_sample.data

import java.time.DayOfWeek
import java.time.LocalDate

/**
 *  달력 Entity Class.
 *  Model에서 Entity에 해당한다.
 *  우선, 날짜 data만을 가진다.
 */

// TODO : 웬만해서 Entity는 ID 필드를 갖고 있게 하는게 좋음 val id : Int
data class CalendarItemEntity (
        val year: Int,
        val month: Int,
        val date: Int
) {
    override fun toString(): String {
        return "${year}년 ${month}월 ${date}일"
    }
}

fun CalendarItemEntity.isToday(): Boolean {
    return year == LocalDate.now().year &&
            month == LocalDate.now().monthValue &&
            date == LocalDate.now().dayOfMonth
}

fun CalendarItemEntity.isSaturday(): Boolean {
    return toLocalDate().dayOfWeek == DayOfWeek.SATURDAY
}

fun CalendarItemEntity.isSunday(): Boolean {
    return toLocalDate().dayOfWeek == DayOfWeek.SUNDAY
}

fun CalendarItemEntity.toLocalDate(): LocalDate {
    return LocalDate.of(year, month, date)
}