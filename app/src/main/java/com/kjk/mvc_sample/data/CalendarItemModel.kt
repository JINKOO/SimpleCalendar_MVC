package com.kjk.mvc_sample.data

import com.kjk.mvc_sample.extension.toCalendarItemEntity
import com.kjk.mvc_sample.extension.toLocalDate
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.collections.ArrayList

/**
 *   달력에서 날짜를 생성하는 부분.
 *   Model에서 비즈니스 로직에 해당하는 부분이다.
 */
class CalendarItemModel : CalendarDataSender {

    private var calendarItemList: ArrayList<CalendarItemEntity> = ArrayList()
    private var baseDate: LocalDate = LocalDate.now()

    /** 달력 data생성 */
    fun fetchCalendarDate() {
        val currentLocalDate = LocalDate.of(baseDate.year, baseDate.monthValue, 1)
        val startDate = currentLocalDate.minusDays(currentLocalDate.dayOfWeek.value.toLong())

        var currentDate = startDate
        repeat(CALENDAR_MAX_GRID_SIZE) {
            currentDate.run {
                toCalendarItemEntity().also {
                    calendarItemList.add(it)
                }
            }
            currentDate = currentDate.run { plusDays(1) }
        }
    }

    fun deleteAllDate() {
        calendarItemList.clear()
    }

    fun setBaseDate(localDate: LocalDate) {
        this.baseDate = localDate
    }

    override fun getBaseDate(): LocalDate {
        return this.baseDate
    }

    override fun isToday(item: CalendarItemEntity): Boolean {
        return item.toLocalDate() == LocalDate.now()
    }

    override fun isSaturday(item: CalendarItemEntity): Boolean {
        return item.toLocalDate().dayOfWeek == DayOfWeek.SATURDAY
    }

    override fun isSunday(item: CalendarItemEntity): Boolean {
        return item.toLocalDate().dayOfWeek == DayOfWeek.SUNDAY
    }

    override fun getItemList(): ArrayList<CalendarItemEntity> {
        return this.calendarItemList
    }

    override fun getSelectedDate(position: Int): LocalDate {
        return getItemList()[position].toLocalDate()
    }

    companion object {
        private const val CALENDAR_MAX_GRID_SIZE = 42
        private const val TAG = "CalendarItemModel"
    }
}