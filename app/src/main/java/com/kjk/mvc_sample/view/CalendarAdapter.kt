package com.kjk.mvc_sample.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.CalendarItemEntity
import com.kjk.mvc_sample.data.toLocalDate
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding
import com.kjk.mvc_sample.extension.isCurrentMonth
import java.util.*
import com.kjk.mvc_sample.extension.isToday
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoField

/**
 *  Adapter에서 Data Layer인 Repository를 사용하는 것은 좋지 않다.(의존성 문제)
 *  Activiy, Fragment를 통해서 Repository의 data를 가져오는 것이 바람직 하다.
 *  Adapter에서는 Data Layer에서 Entity 혹은 VO를 사용해야 한다.
 */
class CalendarAdapter(
        var baseDate: LocalDate,
        var itemList: ArrayList<CalendarItemEntity>
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(
                binding
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(itemList[position], baseDate.monthValue)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

class CalendarViewHolder(
        private val binding: ItemCalendarDateBinding
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    fun bind(calendarItemEntity: CalendarItemEntity, month: Int) {
        binding.apply {
            calendarDate.text = calendarItemEntity.date.toString()

            if (calendarItemEntity.toLocalDate().isCurrentMonth(month)) {
                setDateHeaderColor(calendarItemEntity)
                setDateTextColor(calendarItemEntity)
            } else { // 이전 달, or 다음 달의 날짜라면 회색으로 표시 && 색상 표시 Gone
                calendarDate.setTextColor(Color.GRAY)
                imageViewDayColor.visibility = View.GONE
            }

            root.setOnClickListener {
                Toast.makeText(binding.root.context, calendarItemEntity.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.root -> {
                //TODO 확장성 고려
            }
        }
    }

    /** 각 날짜 칸에서 위의 색상을 지정하는 함수 */
    private fun setDateHeaderColor(calendarItemEntity: CalendarItemEntity) {
        binding.apply {
            when {
                calendarItemEntity.toLocalDate().isToday() -> {
                    imageViewDayColor.setBackgroundColor(Color.GREEN)
                }
                calendarItemEntity.getDayOfWeek() == DayOfWeek.SATURDAY.value -> {
                    imageViewDayColor.setBackgroundColor(Color.BLUE)
                }
                calendarItemEntity.getDayOfWeek() == DayOfWeek.SUNDAY.value -> {
                    imageViewDayColor.setBackgroundColor(Color.RED)
                }
                else -> {
                    imageViewDayColor.setBackgroundColor(Color.WHITE)
                }
            }
        }
    }

    /** 각 날짜 칸에서 날짜 텍스트를 지정하는 것 */
    private fun setDateTextColor(calendarItemEntity: CalendarItemEntity) {
        binding.apply {
            when {
                calendarItemEntity.toLocalDate().isToday() -> {
                    calendarDate.setTextColor(Color.GREEN)
                }
                calendarItemEntity.getDayOfWeek() == DayOfWeek.SATURDAY.value -> {
                    calendarDate.setTextColor(Color.BLUE)
                }
                calendarItemEntity.getDayOfWeek() == DayOfWeek.SUNDAY.value -> {
                    calendarDate.setTextColor(Color.RED)
                }
                else -> {
                    calendarDate.setTextColor(Color.YELLOW)
                }
            }
        }
    }
}