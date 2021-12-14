package com.kjk.mvc_sample.data

/**
 *  달력 Entity Class.
 *  Model에서 Entity에 해당한다.
 *  우선, 날짜 data만을 가진다.
 */

// TODO : 웬만해서 Entity는 ID 필드를 갖고 있게 하는게 좋음 val id : Int
data class CalendarItemEntity (
        val date: Int   // 날짜
)