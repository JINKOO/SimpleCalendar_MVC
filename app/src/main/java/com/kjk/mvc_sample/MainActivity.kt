package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kjk.mvc_sample.data.CalendarItemModel
import com.kjk.mvc_sample.databinding.ActivityMainBinding
import com.kjk.mvc_sample.extension.formatYearMonth
import com.kjk.mvc_sample.extension.toLocalDate
import com.kjk.mvc_sample.view.CalendarAdapter
import com.kjk.mvc_sample.view.OnCalendarItemClicked
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

    // TODO : by lazy와 late init을 어떤경우에 구분해서 쓰시나요? -->
    //        이 코드를 작성했을 때에는 어떠한 경우에 구분해서 사용했는지는 몰랐습니다.
    //        viewBinding을 사용 하는 경우, 보통 20번째 줄 처럼 정의해서 사용한다고 구글링을 통해 알게되었습니다.
    /**
     *  by lazy ::  val 타입(read-only).
     *              val 타입의 어떤 값이 나중에 사용될 것이며, '사용 시' 어떤 값이 들어가는를 정해준다.
     *              다른 변수의 초기화 이후에, 값을 알 수 있는 경우에 사용할 수 있다.
     *
     *  lateinit :: var 타입.
     *              var 타입의 어떤 값이 나중에 사용될 것이며, 타입까지만 지정해준다.
     *              이후에 값을 초기화 해준다.
     *              primitive타입에서는 사용 할 수 없다. null타입이 될 수 없다.
     *              값이 저장되기 전에는 uninitialize상태로 존재한다. 사용전에 반드시 초기화 단계를 거쳐야 한다.
     */
    private val calendarAdapter: CalendarAdapter by lazy {
         CalendarAdapter(
                 model
         )
    }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setListeners()
        setCalendarAdapter()
        setCalendarTitle()
        setCalendarDate()
    }

    private fun setCalendarAdapter() {
        binding.rvCalendar.run {
            layoutManager = GridLayoutManager(this@MainActivity, GRID_LAYOUT_SPAN_COUNT)
            adapter = calendarAdapter
        }
    }

    private fun setListeners() {
        binding.buttonPreMonth.setOnClickListener(this@MainActivity)
        binding.buttonNextMonth.setOnClickListener(this@MainActivity)

        calendarAdapter.setItemClickedListener(object: OnCalendarItemClicked {
            override fun onDateItemClicked(view: View, position: Int) {
                Toast.makeText(applicationContext, model.getItemList()[position].toLocalDate().formatYearMonth(), Toast.LENGTH_SHORT).show()
            }
        })
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
        when(v) {
            binding.buttonPreMonth -> { moveMonth(model.getBaseDate().minusMonths(1)) }
            binding.buttonNextMonth -> { moveMonth(model.getBaseDate().plusMonths(1)) }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val GRID_LAYOUT_SPAN_COUNT = 7
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
}

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