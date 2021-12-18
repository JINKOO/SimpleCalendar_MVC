package com.kjk.mvc_sample.data

import android.util.Log
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

/**
 *   달력에서 날짜를 생성하는 부분.
 *   Model에서 비즈니스 로직에 해당하는 부분이다.
 */
class CalendarItemRepository {

    private var calendarItemLists: ArrayList<CalendarItemEntity> = ArrayList()
    private var baseDate: LocalDate = LocalDate.now()

    /** 달력 data생성 */
//    fun createCalendarDate(year: Int, month: Int) {
//        this.calendar = GregorianCalendar(year, month, 1)
//
//        // 현재 달의 시작 요일
//        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
//
//        // 현재 달의 최대 일수
//        val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//
//        // 달의 시작 요일 전까지 0을 add한다.
//        for (i in 1 until dayOfWeek) {
//            calendarItemLists.add(CalendarItemEntity(0))
//        }
//
//        // 달의 날짜 data (1 ~ 31 or 1 ~ 30) add한다.
//        for (date in 1 .. max) {
//            calendarItemLists.add(CalendarItemEntity(date))
//        }
//    }

    private fun checkDayOfWeek(dayOfWeek: Int): String {
        return when(dayOfWeek) {
            Calendar.SUNDAY -> "일요일"
            Calendar.MONDAY -> "월요일"
            Calendar.TUESDAY -> "화요일"
            Calendar.WEDNESDAY -> "수요일"
            Calendar.THURSDAY -> "목요일"
            Calendar.FRIDAY -> "금요일"
            Calendar.SATURDAY -> "토요일"
            else -> ""
        }
    }

    fun deleteAllDate() {
        calendarItemLists.clear()
    }

    fun getCalendarItemLists() = this.calendarItemLists

    fun setBaseDate(localDate: LocalDate) {
        this.baseDate = localDate
    }

    fun getBaseDate() = this.baseDate
}