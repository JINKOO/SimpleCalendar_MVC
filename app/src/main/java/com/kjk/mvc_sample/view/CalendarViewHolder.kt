package com.kjk.mvc_sample.view

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.*
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding
import com.kjk.mvc_sample.extension.formatAll

class CalendarViewHolder(
        private val binding: ItemCalendarDateBinding,
        private val sender: CalendarDataSender
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        setListener()
    }

    fun bind(item: CalendarItemEntity, month: Int) {
        binding.apply {
            setCalendarDate(item.date)
            if (sender.isCurrentMonth(item, month)) {
                setDateTextColor(item)
                setDateUpperImageColor(item)
            } else {
                calendarDate.setTextColor(Color.DKGRAY)
                imageViewDayColor.setBackgroundColor(Color.DKGRAY)
            }
        }
    }

    private fun setCalendarDate(date: Int) {
        binding.run { calendarDate.text =  date.toString() }
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

    private fun showToast() {
        Toast.makeText(binding.root.context, sender.getSelectedDate(adapterPosition).formatAll(), Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.root -> { showToast() }
        }
    }

    companion object {
        private const val TAG = "CalendarViewHolder"
    }
}