package com.kjk.mvc_sample.view

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.CalendarItemEntity
import com.kjk.mvc_sample.data.CalendarItemRepository
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding
import java.time.LocalDate
import java.util.*

class CalendarAdapter(
    var baseDate: LocalDate,
    var itemList: ArrayList<CalendarItemEntity>,
    var model: CalendarItemRepository
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
                binding,
                model
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(baseDate.year, baseDate.monthValue)
        }
    }

    override fun getItemCount(): Int {
        return model.getCalendarItemLists().size
    }

    class ViewHolder(
            private val binding: ItemCalendarDateBinding,
            private val model: CalendarItemRepository
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            setListener()
        }

        fun bind(year: Int, month: Int) {
            val item = model.getCalendarItemLists()[adapterPosition]
            val calendar = GregorianCalendar(year, month, item.date)

            Log.w("1111", "bind() :: ${year}, ${month}, ${model.getCalendarItemLists()[adapterPosition].date}")
            Log.w("1111", calendar.get(Calendar.DAY_OF_WEEK).toString())

            binding.calendarDate.text = item.date.toString()

            // 0이 있는 곳은 날짜가 생성되지 않아야 하는 곳이다.
            if (item.date == 0) {
                binding.apply {
                    imageViewDayColor.visibility = View.GONE
                    calendarDate.visibility = View.GONE
                }
            }

            // 토요일 색상 지정. 파랑
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                binding.apply {
                    calendarDate.setTextColor(Color.BLUE)
                    imageViewDayColor.setBackgroundColor(Color.BLUE)
                }
            }

            // 일요일 색상 지정. 빨간
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                binding.apply {
                    calendarDate.setTextColor(Color.RED)
                    imageViewDayColor.setBackgroundColor(Color.RED)
                }
            }

            // 오늘 날짜 색상 지정색. 초록
            val today = GregorianCalendar()
            if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    today.get(Calendar.DATE) == item.date) {
                binding.apply {
                    calendarDate.setTextColor(Color.GREEN)
                    imageViewDayColor.setBackgroundColor(Color.GREEN)
                }
            }
        }

        private fun setListener() {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v) {
                binding.root -> {
                    // 날짜가 생성된 부분에서만 한다.
//                    if (model.getCalendarItemLists()[adapterPosition].date != 0) {
//                        Toast.makeText(
//                                binding.root.context,
//                                makeDateString(model.getCurrentCalendar().get(Calendar.YEAR), model.getCurrentCalendar().get(Calendar.MONTH), model.getCalendarItemLists()[adapterPosition].date),
//                                Toast.LENGTH_SHORT)
//                                .show()
//                    }
                }
            }
        }

        private fun makeDateString(year: Int, month: Int, date: Int): String {
            return year.toString() + "년" + " " + (month + 1).toString() + "월" + " " + date.toString() + "일"
        }
    }
}