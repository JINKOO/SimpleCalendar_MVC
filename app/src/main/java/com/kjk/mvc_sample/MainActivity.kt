package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.kjk.mvc_sample.data.CalendarRepository
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.extension.formatYearMonth
import com.kjk.mvc_sample.extension.month
import com.kjk.mvc_sample.extension.year
import com.kjk.mvc_sample.view.CalendarAdapter
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

    private val model = CalendarRepository()
    private lateinit var calendarAdapter: CalendarAdapter
    private val baseDate = GregorianCalendar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdapter()
        setListeners()
        setCalendar(baseDate.year, baseDate.month)
    }

    private fun setAdapter() {
        calendarAdapter = CalendarAdapter(
            model.getCalendarItemLists(),
            GregorianCalendar(baseDate.year, baseDate.month, 1)
        )
        binding.apply {
            rvCalendar.layoutManager = GridLayoutManager(this@MainActivity, 7)
            rvCalendar.adapter = calendarAdapter
        }
    }

    private fun setListeners() {
        binding.apply {
            buttonPreMonth.setOnClickListener(this@MainActivity)
            buttonNextMonth.setOnClickListener(this@MainActivity)
        }
    }

    private fun setCalendar(year: Int, month: Int) {
        model.fetchCalendar(year, month)
        binding.apply {
            fetchCalendarTitle(year, month)
            calendarAdapter.baseCalendar = GregorianCalendar(year, month, 1)
            calendarAdapter.itemList = model.getCalendarItemLists()
            calendarAdapter.notifyDataSetChanged()
        }
    }

    private fun fetchCalendarTitle(year: Int, month: Int) {
        val currentDate = GregorianCalendar(year, month, 1)
        binding.textviewCurrentMonth.text = currentDate.formatYearMonth()
    }

    /** 이전 달, 다음 달 선택 했을 때, 현재 그려져 있는 달력을 지워야 한다.*/
    private fun clearCalendar() {
        model.deleteAllDate()
        calendarAdapter.notifyDataSetChanged()
        binding.rvCalendar.adapter = this.calendarAdapter
    }

    /** 이전 달, 다음 달 이동 로직 */
    override fun onClick(v: View?) {
        when (v) {
            binding.buttonPreMonth -> moveMonth(-1)
            binding.buttonNextMonth -> moveMonth(1)
        }
    }

    private fun moveMonth(amount: Int) {
        baseDate.month += amount
        clearCalendar()
        setCalendar(baseDate.year, baseDate.month)
    }
}