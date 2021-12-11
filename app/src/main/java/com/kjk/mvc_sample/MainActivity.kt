package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemRepository
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.view.CalendarAdapter
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

    /** 기존에 GregorianCalendar를 사용했지만,
     * LocalDateTime을 실무에서 많이 사용하므로, 리팩토링 코드에서는 LocalDateTime을 사용한다.
     */
    // 현재 시간 받아오기
    private val baseDate = LocalDateTime.now()
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
        calendarAdapter = CalendarAdapter(baseDate.year, baseDate.monthValue)
    }

    private fun setListeners() {
        binding.buttonPreMonth.setOnClickListener(this@MainActivity)
        binding.buttonNextMonth.setOnClickListener(this@MainActivity)
    }

    private fun setCalendar(year: Int, month: Int) {
        /** Adapter는 1번만 초기화 하는 것이 좋다. */
//        this.adapter = CalendarAdapter(year, month, model)

        binding.apply {
            textviewCurrentMonth.text = makeCurrentDateString(year, month)
            rvCalendar.layoutManager = GridLayoutManager(this@MainActivity, 7)
            rvCalendar.adapter = calendarAdapter
        }
        model.createCalendarDate(year, month)
    }

    private fun makeCurrentDateString(year: Int, month: Int): String {
        val currentDate = GregorianCalendar(year, month, 1)
        return currentDate.get(Calendar.YEAR).toString() + "년" + " " + (currentDate.get(Calendar.MONTH) + 1).toString() + "월"
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
            binding.buttonPreMonth -> {moveAmount(1)}
            binding.buttonNextMonth -> {moveAmount(-1)}
        }
    }

    private fun moveAmount(amount: Int) {
        baseDate.monthValue += amount
        clearCalendar()
        setCalendar(year, baseDate.monthValue)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}