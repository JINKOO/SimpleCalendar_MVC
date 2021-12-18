package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemRepository
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.extension.formatAll
import com.kjk.mvc_sample.extension.formatYearMonth
import com.kjk.mvc_sample.view.CalendarAdapter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 *  리사이클러 뷰를 사용해서
 *  달력을 만든다.
 *  Controller 및 View(xml)에 해당.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val model = CalendarItemRepository()
    private lateinit var calendarAdapter: CalendarAdapter
    private var baseDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdapter()
        setListeners()
        setCalendar(baseDate.year, baseDate.monthValue)
    }

    private fun setAdapter() {
        calendarAdapter = CalendarAdapter(
                baseDate,
                model.getCalendarItemLists()
        )

        binding.apply {
            rvCalendar.layoutManager = GridLayoutManager(this@MainActivity, 7)
            rvCalendar.adapter = calendarAdapter
        }
    }

    private fun setListeners() {
        binding.buttonPreMonth.setOnClickListener(this@MainActivity)
        binding.buttonNextMonth.setOnClickListener(this@MainActivity)
    }

    private fun setCalendar(year: Int, month: Int) {
        model.fetchCalendarData(year, month)
        fetchCalendarTitle(year, month)
        binding.apply {
            calendarAdapter.baseDate = baseDate
            calendarAdapter.itemList = model.getCalendarItemLists()
            calendarAdapter.notifyDataSetChanged()
        }
    }

    /** 달력의 년도, 월 Title set */
    private fun fetchCalendarTitle(year: Int, month: Int) {
        val currentYearMonth = LocalDate.of(year, month, 1).formatYearMonth()
        binding.apply { textviewCurrentMonth.text =  currentYearMonth }
    }

    /** 이전 달, 다음 달 선택 했을 때, 현재 그려져 있는 달력을 지워야 한다.*/
    private fun clearCalendar() {
        model.deleteAllDate()
        calendarAdapter.notifyDataSetChanged()
        binding.rvCalendar.adapter = calendarAdapter
    }

    /** 이전 달, 다음 달 이동 로직 */
    override fun onClick(v: View?) {
        when(v) {
            binding.buttonPreMonth -> { moveMonth(baseDate.minusMonths(1)) }
            binding.buttonNextMonth -> { moveMonth(baseDate.plusMonths(1)) }
        }
    }

    private fun moveMonth(changeDate: LocalDate) {
        baseDate = changeDate
        Log.d(TAG, "moveAmount: ${changeDate.year}, ${changeDate.monthValue}")
        clearCalendar()
        setCalendar(baseDate.year, baseDate.monthValue)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}