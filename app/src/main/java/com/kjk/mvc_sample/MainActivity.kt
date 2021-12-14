package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemModel
import com.kjk.mvc_sample.databinding.ActivityMainBinding
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

    private val model = CalendarItemModel()

    //TODO : by lazy와 late init을 어떤경우에 구분해서 쓰시나요?
    private lateinit var adapter: CalendarAdapter


    // TODO : 얘네 전부 모델에 있어야할 놈들
    private val today = GregorianCalendar()
    private var year = today.get(Calendar.YEAR)
    private var month = today.get(Calendar.MONTH)
    ///////////////////////////////////////////////




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: log w  레벨을 쓰는 이유는 뭔가요?
        Log.w("1111", "current :: ${year}, ${month + 1}")

        initLayoutValues()
        setListeners()
        setCalendar(year, month)
    }

    private fun initLayoutValues() {
        setContentView(binding.root)
    }


    private fun createLayoutManager(): GridLayoutManager {
        return GridLayoutManager(this, 7)
    }

    private fun setListeners() {
        binding.buttonPreMonth.setOnClickListener(this)
        binding.buttonNextMonth.setOnClickListener(this)
    }

    private fun setCalendar(year: Int, month: Int) {
        this.adapter = CalendarAdapter(year, month, model)

        binding.apply {
            //TODO : 이 경우 apply의 용법이 잘못된 것 같습니다.
            textviewCurrentMonth.text = makeCurrentDateString(year, month)
            // 리사이클러 뷰
            rvCalendar.layoutManager = createLayoutManager()
            rvCalendar.adapter = adapter
        }

        model.createCalendarDate(year, month)
    }

    private fun makeCurrentDateString(year: Int, month: Int): String {
        //TODO : 이 놈은 모델에 있어야할 펑션입니다.
        val currentDate = GregorianCalendar(year, month, 1)
        return currentDate.get(Calendar.YEAR).toString() + "년" + " " + (currentDate.get(Calendar.MONTH) + 1).toString() + "월"
    }

    /** 이전 달, 다음 달 선택 했을 때, 현재 그려져 있는 달력을 지워야 한다.*/
    private fun clearCalendar() {
        model.deleteAllDate()
        adapter.notifyDataSetChanged()
        binding.rvCalendar.adapter = this.adapter // TODO : 어댑터를 왜 다씨 주입하죠?
    }

    /** 이전 달, 다음 달 이동 로직 */
    override fun onClick(v: View?) {
        when(v) {
            binding.buttonPreMonth -> {
                Log.w("1111", "preMonthBtn Clicked")
                month--
                clearCalendar()
                setCalendar(year, month)
            }

            binding.buttonNextMonth -> {
                Log.w("1111", "nextMonthBtn Clicked")
                month++
                clearCalendar()
                setCalendar(year, month)
            }
        }
    }
}