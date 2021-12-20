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

    private var calendarItemList: ArrayList<CalendarItemEntity> = ArrayList()
    private var baseDate: LocalDate = LocalDate.now()

    /** 달력 data생성 */
    // TODO : createCalendarDate라는 펑션이 지금 하는일이 많아요
    //      --> 함수 이름 변경했습니다.
    // TODO : 캘린더 변수 초기화 / 월의 첫 요일 얻기 / 월의 마지막 요일 얻기 / 처음 시작하는 요일을 위해 리스트에 0 추가 / 1일부터 31일까지 추가
    // TODO : 이 많은 일들은 각각의 펑션으로 분리하고 최상위 펑션에서 각가그이 펑션을 호출만 해서 , 보는자로 하여금 코드 플로우를 알수있게 작성해야합니다.
    fun fetchCalendarDate(year: Int, month: Int) {
//        // 현재 달의 시작 요일
//        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
//
//        // 현재 달의 최대 일수
//        val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//
//        Log.w("1111", "createCalendarDate() :: ${dayOfWeek}, ${checkDayOfWeek(dayOfWeek)}, ${max}")
//
//        // 달의 시작 요일 전까지 0을 add한다.
//        // TODO : 다른 사람이 아래 1을 보면 어떤 의민지 알수 있을까요? ,  이런 경우를 위해 의미를 단박에 알수 없는 상수값의경 상수변수로 관리합니다. 변수 네이밍을 해서 .
//        for (i in 1 until dayOfWeek) {
//            calendarItemLists.add(CalendarItemEntity(0))
//        }
//
//        // 달의 날짜 data (1 ~ 31 or 1 ~ 30) add한다.
//        for (date in 1 .. max) {
//            calendarItemLists.add(CalendarItemEntity(date))
//        }

        val currentLocalDate = LocalDate.of(year, month, 1)
        val startDayOfWeek = currentLocalDate.dayOfWeek.value
        val startDate = currentLocalDate.minusDays(startDayOfWeek.toLong())

        var currentDate = startDate
        repeat(CALENDAR_MAX_GRID_SIZE) {
            currentDate.apply {
                toCalendarItemEntity().also {
                    calendarItemList.add(it) }
            }
            currentDate = currentDate.run { plusDays(1) }
        }
    }

    // TODO : 아래와 같은 요일에 대한 상수는 멤버 변수 또는 companyon OBject같은 스태틱 변수 또는  Enum  or Sealed class로 관리 바람
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
        calendarItemList.clear()
    }

    fun setBaseDate(localDate: LocalDate) {
        this.baseDate = localDate
    }

    fun getBaseDate() = this.baseDate

    fun getCalendarItemLists() = this.calendarItemList

    companion object {
        private const val CALENDAR_MAX_GRID_SIZE = 42
        private const val TAG = "CalendarItemRepository"
    }
}