package com.kjk.mvc_sample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.CalendarItemEntity
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding
import java.time.LocalDate
import java.util.*

class CalendarAdapter(
        // TODO : 이런 값들은 직접 전달 하는것이아닌, 모델을 통해서 getYear(), getMonth() 와 같이 받아와야 함
        var baseDate: LocalDate,
        var itemList: ArrayList<CalendarItemEntity>
        //        private val model: CalendarItemRepository
        // TODO: 의존성을 낮추려면, 모델의 펑션들을 추상화 한 인터페이스만을 전달해야함 -->
        //       --> model과 controller 의존성분리를 위해, Adapter에서는 model을 참조하지 않도록 함.
        //           model의 값이 필요한 경우, view(Activity)를 통해서 가져오도록 수정하였습니다.
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(
                binding
//                model
            // TODO : 의존성을 낮추려면, 모델의 펑션들을 추상화 한 인터페이스만을 전달해야함 -->
            //      --> model과 controller 의존성분리를 위해, Adapter에서는 model을 참조하지 않도록 함.
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(itemList[position], baseDate.monthValue)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

// TODO :  뷰홀더는 이왕이면 같은 File내부에 말고, 외부에 따로 파일관리 하는 것을 추천, 또한 같은 파일에 선언하더라고 특정 Class내부에 이런식으로 선언하지 말것 -->
//      --> 외부 File에서 CalendarViewHolder로 정의했습니다. 그런데 외부에서 ViewHolder를 관리하는 것이 특별한 이유가 있나요??
//class CalendarViewHolder(
//        private val binding: ItemCalendarDateBinding,
//        private val model: CalendarItemRepository
//) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
//
//    init {
//        setListener()
//    }
//
//
//    // TODO : 아래에 공통적으로 쓰이지만 조건에 따라 다른 Value를 셋 하는 펑션은 공통 펑션으로 하나 빼서 bind() 펑션안에서는 그 공통 펑션에 파라미터값을 전달해 호출만 하도록 수정 바람
//    // TODO : bind()처럼 하나의 펑션에 너무 많은 "로직"들이 존재하면 가독성 매우 떨어짐 , 또한 펑션 콜의 플로우를 볼수 없음 .
//    fun bind(year: Int, month: Int) {
//        val item = model.getCalendarItemLists()[adapterPosition]
//        val calendar = GregorianCalendar(year, month, item.date)
//
//        Log.w("1111", "bind() :: ${year}, ${month}, ${model.getCalendarItemLists()[adapterPosition].date}")
//        Log.w("1111", calendar.get(Calendar.DAY_OF_WEEK).toString())
//
//        binding.calendarDate.text = item.date.toString()
//
//        // 0이 있는 곳은 날짜가 생성되지 않아야 하는 곳이다.
//
//        //TODO : 아래의 IF문들은 모두 Model에서 검사하고 결과값만 호출받아 View의 세팅만 할 것
//        // TODO : 아래는 예시
//        //TODO : if(model.isdayOfWeeks(position)){ ~~ }
//        //TODO : if(model.isSunday(position)){ ~~ }
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
//    }
//
//    private fun setListener() {
//        binding.root.setOnClickListener(this)
//    }
//
//    //TODO : oncliCK에서 바로 로직을 놓지말고, 따로 펑션을 분리한뒤 펑션 호출만 놓을 수 있도록 바람.
//    override fun onClick(v: View?) {
//        when(v) {
//            binding.root -> {
//                // 날짜가 생성된 부분에서만 한다.
//                if (model.getCalendarItemLists()[adapterPosition].date != 0) {
//                    Toast.makeText(
//                            binding.root.context,
//                            makeDateString(model.getCurrentCalendar().get(Calendar.YEAR), model.getCurrentCalendar().get(Calendar.MONTH), model.getCalendarItemLists()[adapterPosition].date),
//                            Toast.LENGTH_SHORT)
//                            .show()
//                }
//            }
//        }
//    }
//
//    // TODO : 년, 월, 일 같은 워딩들은 모두 상수 이므로 코드로직 상에 하드코딩으로 주입하지 말고, 클래스 맴버 필드나, companyon OBject에서 관리
//    private fun makeDateString(year: Int, month: Int, date: Int): String {
//        return year.toString() + "년" + " " + (month + 1).toString() + "월" + " " + date.toString() + "일"
//    }
//}