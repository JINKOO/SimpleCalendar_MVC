package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemModel
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.view.CalendarAdapter

/**
 *  리사이클러 뷰를 사용해서
 *  달력을 만든다.
 *  유의해야하는 점은 이것이다.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter: CalendarAdapter by lazy {
        CalendarAdapter(model)
    }

    private val model = CalendarItemModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayoutValues()
        setListeners()

        setCalendar()
    }

    private fun initLayoutValues() {
        setContentView(binding.root)

        // 리사이클러 뷰
        binding.apply {
            rvCalendar.layoutManager = createLayoutManager()
            rvCalendar.adapter = adapter
        }
    }

    private fun createLayoutManager(): StaggeredGridLayoutManager {
        return StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun setListeners() {
        binding.buttonPreMonth.setOnClickListener(this)
        binding.buttonNextMonth.setOnClickListener(this)
    }

    private fun setCalendar() {
        model.createCalendarItem()
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.buttonPreMonth -> {
                Log.w("1111", "preMonthBtn Clicked")
            }

            binding.buttonNextMonth -> {
                Log.w("1111", "nextMonthBtn Clicked")
            }
        }
    }
}