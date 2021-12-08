package com.kjk.mvc_sample.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.CalendarItemEntity
import com.kjk.mvc_sample.data.toCalendar
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding
import com.kjk.mvc_sample.extension.isCurrentMonth
import com.kjk.mvc_sample.extension.isToday
import java.util.*

class CalendarAdapter(
    var itemList: List<CalendarItemEntity>,
    var baseCalendar: Calendar
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position], baseCalendar.get(Calendar.MONTH))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    companion object {
        private const val TAG = "CalendarAdapter"
    }
}

class ViewHolder(
    private val binding: ItemCalendarDateBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        private const val TAG = "CalendarAdapter"
    }

    fun bind(calendarItemEntity: CalendarItemEntity, month: Int) {
        binding.apply {
            calendarDate.text = calendarItemEntity.date.toString()

            if (calendarItemEntity.toCalendar().isCurrentMonth(month)) {
                setDateHeaderColor(calendarItemEntity)
                setDateTextColor(calendarItemEntity)
            } else {
                calendarDate.setTextColor(Color.GRAY)
            }

            binding.root.setOnClickListener {
                if (calendarItemEntity.date != 0) {
                    Toast.makeText(
                        binding.root.context,
                        calendarItemEntity.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setDateHeaderColor(calendarItemEntity: CalendarItemEntity) {
        binding.apply {
            when {
                calendarItemEntity.getDayOfWeek() == Calendar.SATURDAY -> {
                    imageViewDayColor.setBackgroundColor(Color.BLUE)
                }
                calendarItemEntity.getDayOfWeek() == Calendar.SUNDAY -> {
                    imageViewDayColor.setBackgroundColor(Color.RED)
                }
                calendarItemEntity.toCalendar().isToday() -> {
                    imageViewDayColor.setBackgroundColor(Color.GREEN)
                }
            }
        }
    }

    private fun setDateTextColor(calendarItemEntity: CalendarItemEntity) {
        binding.apply {
            when {
                calendarItemEntity.getDayOfWeek() == Calendar.SATURDAY -> {
                    calendarDate.setTextColor(Color.BLUE)
                }
                calendarItemEntity.getDayOfWeek() == Calendar.SUNDAY -> {
                    calendarDate.setTextColor(Color.RED)
                }
                calendarItemEntity.toCalendar().isToday() -> {
                    calendarDate.setTextColor(Color.GREEN)
                }
                else -> calendarDate.setTextColor(Color.BLACK)
            }
        }
    }
}