package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemModel
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.view.CalendarAdapter
import java.time.Year
import java.util.*

/**
 *  리사이클러 뷰를 사용해서
 *  달력을 만든다.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val model = CalendarItemModel()

    private val current = GregorianCalendar()

    private var year = current.get(Calendar.YEAR)
    private var month = current.get(Calendar.MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.w("1111", "current :: ${year}, ${month + 1}")

        initLayoutValues()
        setListeners()

        setCalendar(year, month)
    }

    private fun initLayoutValues() {
        setContentView(binding.root)

//        // 리사이클러 뷰
//        binding.apply {
//            rvCalendar.layoutManager = createLayoutManager()
//            rvCalendar.adapter = adapter
//        }
    }

    private fun createLayoutManager(): StaggeredGridLayoutManager {
        return StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun setListeners() {
        binding.buttonPreMonth.setOnClickListener(this)
        binding.buttonNextMonth.setOnClickListener(this)
    }

    private fun setCalendar(year: Int, month: Int) {
        val adapter = CalendarAdapter(year, month, model)

        binding.apply {

            textviewCurrentMonth.text = year.toString() + "년" + " " + (month + 1).toString() + "월"

            // 리사이클러 뷰
            rvCalendar.layoutManager = createLayoutManager()
            rvCalendar.adapter = adapter
        }

        model.createCalendarDate(year, month)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.buttonPreMonth -> {
                Log.w("1111", "preMonthBtn Clicked")
                month--
                setCalendar(year, month)
            }

            binding.buttonNextMonth -> {
                Log.w("1111", "nextMonthBtn Clicked")
                month++
                setCalendar(year, month)
            }
        }
    }
}