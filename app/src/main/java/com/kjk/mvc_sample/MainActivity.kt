package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemModel
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.extension.formatYearMonth
import com.kjk.mvc_sample.view.CalendarAdapter
import java.time.LocalDate
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

    private val calendarAdapter: CalendarAdapter by lazy {
        CalendarAdapter(model)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
        initCalendarData()
    }

    private fun setListeners() {
        binding.buttonPreMonth.setOnClickListener(this@MainActivity)
        binding.buttonNextMonth.setOnClickListener(this@MainActivity)
    }

    private fun initCalendarData() {
        setCalendarTitle()
        setCalendarLayout()
        setCalendarDate()
    }

    private fun setCalendarLayout() {
        binding.rvCalendar.run {
            layoutManager = GridLayoutManager(this@MainActivity, GRID_LAYOUT_SPAN_COUNT)
            adapter = calendarAdapter
        }
    }

    private fun setCalendarTitle() {
        binding.apply { textviewCurrentMonth.text = model.getBaseDate().formatYearMonth() }
    }

    private fun setCalendarDate() {
        model.fetchCalendarDate()
        calendarAdapter.run {
            notifyDataSetChanged()
        }
    }

    /** 이전 달, 다음 달 선택 했을 때, 현재 그려져 있는 달력을 지워야 한다.*/
    private fun clearCalendar() {
        model.deleteAllDate()
        calendarAdapter.notifyDataSetChanged()
    }

    /** 이전 달, 다음 달 이동 로직 */
    private fun moveMonth(changedLocalDate: LocalDate) {
        model.setBaseDate(changedLocalDate)
        clearCalendar()
        setCalendarTitle()
        setCalendarDate()
    }

    override fun onClick(v: View?) {
        when (v) {
            //Todo : 굳이  model에서 가져온 getBaseDate를 다시 moveMonth로 전달해서 model.sedtBaseDate에 또 넣을 필요가 있을까요
                // Todo : 저라면 moveMonth(actionType : ActionType)으로  앞버튼인지, 뒤 버튼인지만 보내면 될것 같은데,
            binding.buttonPreMonth -> { moveMonth(model.getBaseDate().minusMonths(MONTH_TO_MOVE)) }
            binding.buttonNextMonth -> { moveMonth(model.getBaseDate().plusMonths(MONTH_TO_MOVE)) }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val GRID_LAYOUT_SPAN_COUNT = 7
        private const val MONTH_TO_MOVE = 1L
    }
}

//    fun notifyView(){
//        view.text = model.getName ()
//        sdkjfklds
//        sdf
//        sdf
//        sdf
//        sdf
//
//    }

    /** Log 사용 법 */
//    fun makeLog( msg : String?, level : String){
//        makelog("sdfjkhskjdfhjksdhfkjhsdfk")
//
//        if(BuildConfig.DEBUG){
//            when( level){
//                "w" ->{
//                    Log.w(title, msg)
//                }
//                "e" ->{
//                    Log.e()
//                }
//            }
//        }
//        else{
//
//        }
//    }
//

//interface ViewNotifyCallback {
//    fun notify(state : State )
//}
//
//// 월을 바꿧어 -> REDRAW_MONTH
//// 년을 바꿧어 -> REDRAW_MONTH
//.. 일을 바꿧어 -> REDRAW_MONTH
//// 전체를 바꿧어 - > REDRAW_MONTH
//// 월만 다시 그려줘 - REDRAW_MONTH
///
//
//enum class ViewState {
//    PARTITION , ALL, NEXTPAGE
//}