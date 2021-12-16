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

    fun deleteAllDate() {
        calendarItemLists.clear()
    }

    fun getCalendarItemLists() = this.calendarItemLists

    companion object {
        private const val TAG = "CalendarItemRepository"
    }
}