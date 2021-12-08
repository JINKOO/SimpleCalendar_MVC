package com.kjk.mvc_sample.extension

import java.text.SimpleDateFormat
import java.util.*

private val sdfAll = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
private val sdfMonth = SimpleDateFormat("yyyy년 MM월", Locale.KOREA)

fun Calendar.formatAll() = sdfAll.format(time)
fun Calendar.formatYearMonth() = sdfMonth.format(time)

fun Calendar.isToday(): Boolean {
    val today = GregorianCalendar()
    return today.get(Calendar.YEAR) == this.get(Calendar.YEAR) &&
            today.get(Calendar.MONTH) == this.get(Calendar.MONTH) &&
            today.get(Calendar.DATE) == this.get(Calendar.DATE)
}

fun Calendar.isCurrentMonth(currentMonth: Int): Boolean {
    return get(Calendar.MONTH) == currentMonth
}

var Calendar.year: Int
    get() = get(Calendar.YEAR)
    set(value) = set(Calendar.YEAR, value)

var Calendar.month: Int
    get() = get(Calendar.MONTH)
    set(value) = set(Calendar.MONTH, value)

var Calendar.day: Int
    get() = get(Calendar.DAY_OF_MONTH)
    set(value) = set(Calendar.DAY_OF_MONTH, value)