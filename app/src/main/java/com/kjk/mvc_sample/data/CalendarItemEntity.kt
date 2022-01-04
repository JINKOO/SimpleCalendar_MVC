package com.kjk.mvc_sample.data

import java.time.DayOfWeek
import java.time.LocalDate

/**
 *  달력 Entity Class.
 *  Model에서 Entity에 해당한다.
 *  우선, 날짜 data만을 가진다.
 */

data class CalendarItemEntity (
        val year: Int,
        val month: Int,
        val date: Int
) {
    override fun toString(): String {
        return "${year}년 ${month}월 ${date}일"
    }
}