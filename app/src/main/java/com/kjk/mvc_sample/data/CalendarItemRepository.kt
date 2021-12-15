package com.kjk.mvc_sample.data

import android.util.Log
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

/**
 *   달력에서 날짜를 생성하는 부분. Data Layer에 해당한다.
 *   Model에서 비즈니스 로직에 해당하는 부분이다.
 *   Repository로 이름을 변경한다.
 *
 */
class CalendarItemRepository {

    private var calendarItemLists: ArrayList<CalendarItemEntity> = ArrayList()
    private lateinit var calendar: GregorianCalendar

    /** 달력 data생성 */
    fun createCalendarDate(year: Int, month: Int) {
//        this.calendar = GregorianCalendar(year, month, 1)
//
//        // 현재 달의 시작 요일
//        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
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
    }

    /** 현재 달의 날짜를 생성하는 부분 수정 */
    fun fetchCalendarData(year: Int, month: Int) {
        val localDate = LocalDate.of(year, month, 1)

        // 현재 달의 시작 요일
        val dayOfWeek = localDate.dayOfWeek.value
        val startDate = localDate.run {
            minusDays(dayOfWeek.toLong())
        }

        Log.w(TAG, "fetchCalendarData: ${localDate}, ${dayOfWeek}, ${startDate}")

        var currentDate = startDate
        repeat(42) { it ->
            Log.w(TAG, "fetchCalendarData: ${it}, ${currentDate}")
            currentDate.toCalendarItemEntity().also {
                calendarItemLists.add(it)
            }
            currentDate = currentDate.run { plusDays(1) }
        }
    }

    /*
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
     */

    fun deleteAllDate() {
        calendarItemLists.clear()
    }

    fun getCurrentCalendar() = this.calendar

    fun getCalendarItemLists() = this.calendarItemLists

    companion object {
        private const val TAG = "CalendarItemRepository"
    }
}