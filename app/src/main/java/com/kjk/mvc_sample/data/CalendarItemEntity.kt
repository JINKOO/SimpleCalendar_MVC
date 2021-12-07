package com.kjk.mvc_sample.data

/**
 *  달력 Entity Class.
 *  Model에서 Entity에 해당한다.
 *  우선, 날짜 data만을 가진다.
 */
data class CalendarItemEntity (
        val date: Int   // 날짜
)