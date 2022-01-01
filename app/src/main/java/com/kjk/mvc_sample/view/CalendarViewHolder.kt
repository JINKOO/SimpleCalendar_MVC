package com.kjk.mvc_sample.view

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.*
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding
import com.kjk.mvc_sample.extension.formatAll
import com.kjk.mvc_sample.extension.isCurrentMonth
import com.kjk.mvc_sample.extension.toLocalDate
import java.time.LocalDate

class CalendarViewHolder(
        private val binding: ItemCalendarDateBinding,
        private val sender: CalendarDataSender
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        setListener()
    }

    // TODO : 아래에 공통적으로 쓰이지만 조건에 따라 다른 Value를 셋 하는 펑션은 공통 펑션으로 하나 빼서 bind() 펑션안에서는 그 공통 펑션에 파라미터값을 전달해 호출만 하도록 수정 바람 -->
    //      --> bind(param) param을 조건에 따라 다른 value를 set하는 fun(param) 으로 전달하도록 수정.
    // TODO : bind()처럼 하나의 펑션에 너무 많은 "로직"들이 존재하면 가독성 매우 떨어짐 , 또한 펑션 콜의 플로우를 볼수 없음 . -->
    //      --> 수정했습니다.
    fun bind(item: CalendarItemEntity, month: Int) {

//        binding.calendarDate.text = item.date.toString()

        // 0이 있는 곳은 날짜가 생성되지 않아야 하는 곳이다.
        // TODO : 아래의 IF문들은 모두 Model에서 검사하고 결과값만 호출받아 View의 세팅만 할 것
        // TODO : 아래는 예시
        // TODO : if(model.isdayOfWeeks(position)){ ~~ }
        // TODO : if(model.isSunday(position)){ ~~ }
        //       --> model과 view간의 의존성을 없애기 위해, 확장 함수로 각 함수를 정의해 사용했습니다.
        //       --> interface명세서를 만들고, 구현체를 넘김.
//        if (item.date == 0) {
//            binding.apply {
//                imageViewDayColor.visibility = View.GONE
//                calendarDate.visibility = View.GONE
//            }
//        }
//
//        // 토요일 색상 지정. 파랑
//        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//            binding.apply {
//                calendarDate.setTextColor(Color.BLUE)
//                imageViewDayColor.setBackgroundColor(Color.BLUE)
//            }
//        }
//
//        // 일요일 색상 지정. 빨간
//        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//            binding.apply {
//                calendarDate.setTextColor(Color.RED)
//                imageViewDayColor.setBackgroundColor(Color.RED)
//            }
//        }
//
//        // 오늘 날짜 색상 지정색. 초록
//        val today = GregorianCalendar()



//        if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
//                today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
//                today.get(Calendar.DATE) == item.date) {
//            binding.apply {
//                calendarDate.setTextColor(Color.GREEN)
//                imageViewDayColor.setBackgroundColor(Color.GREEN)
//            }
//        }

        binding.apply {
            calendarDate.text = item.date.toString()
            Log.w(TAG, "bind: ${item.month}, ${month}")
            if (item.toLocalDate().isCurrentMonth(month)) {
                setDateTextColor(item)
                setDateUpperImageColor(item)
            } else {
                calendarDate.setTextColor(Color.DKGRAY)
                imageViewDayColor.setBackgroundColor(Color.DKGRAY)
            }
        }
    }

    /** 요일 색상 지정 */
    private fun setDateTextColor(item: CalendarItemEntity) {
       binding.apply {
           when {
               sender.isToday(item) -> { calendarDate.setTextColor(Color.GREEN) }
               sender.isSaturday(item) -> { calendarDate.setTextColor(Color.BLUE) }
               sender.isSunday(item) -> { calendarDate.setTextColor(Color.RED) }
               else -> {calendarDate.setTextColor(Color.LTGRAY)}
           }
       }
    }

    /** 각 날짜 칸 색상 지정 */
    private fun setDateUpperImageColor(item: CalendarItemEntity) {
        binding.apply {
            when {
                sender.isToday(item) -> { imageViewDayColor.setBackgroundColor(Color.GREEN) }
                sender.isSaturday(item) -> { imageViewDayColor.setBackgroundColor(Color.BLUE) }
                sender.isSunday(item) -> { imageViewDayColor.setBackgroundColor(Color.RED) }
                else -> { imageViewDayColor.setBackgroundColor(Color.WHITE) }
            }
        }
    }

    private fun setListener() {
        binding.root.setOnClickListener(this)
    }

    // TODO : oncliCK에서 바로 로직을 놓지말고, 따로 펑션을 분리한뒤 펑션 호출만 놓을 수 있도록 바람. -->
    //      --> function으로 분리했습니다.
    override fun onClick(v: View?) {
        when(v) {
            binding.root -> { toastClickedDate() }
        }
    }

    private fun toastClickedDate() {
        Toast.makeText(binding.root.context, LocalDate.now().formatAll(), Toast.LENGTH_SHORT).show()
    }

    // TODO : 년, 월, 일 같은 워딩들은 모두 상수 이므로 코드로직 상에 하드코딩으로 주입하지 말고, 클래스 맴버 필드나, companyon OBject에서 관리 -->
    //        --> 확장 함수로 분리했습니다.
    private fun makeDateString(year: Int, month: Int, date: Int): String {
        return year.toString() + "년" + " " + (month + 1).toString() + "월" + " " + date.toString() + "일"
    }

    companion object {
        private const val TAG = "CalendarViewHolder"
    }
}