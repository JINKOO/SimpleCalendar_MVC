package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemRepository
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.view.CalendarAdapter
import java.time.LocalDate
import java.time.LocalDateTime
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

    /**
     * 기존에 GregorianCalendar를 사용했지만,
     * LocalDateTime을 실무에서 많이 사용하므로, 리팩토링 코드에서는 LocalDateTime을 사용한다.
     */
    // 현재 시간 받아오기
    private var baseDate = LocalDate.now()
    private var year = baseDate.year
    private var month = baseDate.monthValue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ${baseDate}, ${year}, ${month}")
        setContentView(binding.root)
        setAdapter()
        setListeners()
        setCalendar(year, month)
    }

    private fun setAdapter() {
        calendarAdapter = CalendarAdapter(
                baseDate.year,
                baseDate.monthValue,
                baseDate.dayOfMonth,
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
        Log.d(TAG, "setCalendar: ${year},  ${month}")
        model.fetchCalendarData(year, month)
        binding.apply {
            textviewCurrentMonth.text = makeCurrentDateString(year, month)
            calendarAdapter.year = year
            calendarAdapter.month = month
            calendarAdapter.itemList = model.getCalendarItemLists()
            calendarAdapter.notifyDataSetChanged()
        }
    }

    private fun makeCurrentDateString(year: Int, month: Int): String {
        val currentDate = LocalDate.of(year, month, 1)
        return currentDate.year.toString() + "년" + " " + currentDate.monthValue.toString() + "월"
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