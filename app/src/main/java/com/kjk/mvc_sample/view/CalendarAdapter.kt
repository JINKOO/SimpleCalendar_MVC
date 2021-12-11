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
import java.util.*
import com.kjk.mvc_sample.extension.isToday
import java.time.LocalDate

/**
 *  Adapter에서 Data Layer인 Repository를 사용하는 것은 좋지 않다.(의존성 문제)
 *  Activiy, Fragment를 통해서 Repository의 data를 가져오는 것이 바람직 하다.
 *  Adapter에서는 Data Layer에서 Entity 혹은 VO를 사용해야 한다.
 */
class CalendarAdapter(
        private val year: Int,
        private val month: Int,
        private val date: Int,
        private val itemList: ArrayList<CalendarItemEntity>
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(
        private val binding: ItemCalendarDateBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            setListener()
        }

        // bind에서는 굳이 포지션 값이 필요없다. 현재 포지션에 그려주는 것이기 때문에 position값이 필요없다.
        fun bind(calendarItemEntity: CalendarItemEntity, month: Int) {
            val item = repository.getCalendarItemLists()[adapterPosition]
            val calendar = GregorianCalendar(year, month, item.date)

            binding.calendarDate.text = item.date.toString()

            setDateHeaderColor(calendarItemEntity)
            setDateTextColor(calendarItemEntity)

            // 0이 있는 곳은 날짜가 생성되지 않아야 하는 곳이다.
            if (item.date == 0) {
                binding.apply {
                    imageViewDayColor.visibility = View.GONE
                    calendarDate.visibility = View.GONE
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
                    if (repository.getCalendarItemLists()[adapterPosition].date != 0) {
                        Toast.makeText(
                            binding.root.context,
                            makeDateString(repository.getCurrentCalendar().get(Calendar.YEAR), repository.getCurrentCalendar().get(Calendar.MONTH), repository.getCalendarItemLists()[adapterPosition].date),
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        private fun makeDateString(year: Int, month: Int, date: Int): String {
            return year.toString() + "년" + " " + (month + 1).toString() + "월" + " " + date.toString() + "일"
        }

        private fun setDateHeaderColor(calendarItemEntity: CalendarItemEntity) {
            binding.apply {
                when {
                    calendarItemEntity.toLocalDate().isToday() -> {
                        imageViewDayColor.setBackgroundColor(Color.GREEN)
                    }
                    calendarItemEntity.getDayOfWeek() == Calendar.SATURDAY -> {
                        imageViewDayColor.setBackgroundColor(Color.BLUE)
                    }
                    calendarItemEntity.getDayOfWeek() == Calendar.SUNDAY -> {
                        imageViewDayColor.setBackgroundColor(Color.RED)
                    }
                    else -> {
                        imageViewDayColor.setBackgroundColor(Color.WHITE)
                    }
                }
            }
        }

        private fun setDateTextColor(calendarItemEntity: CalendarItemEntity) {
            binding.apply {
                when {
                    calendarItemEntity.toLocalDate().isToday() -> {
                        calendarDate.setTextColor(Color.GREEN)
                    }
                    calendarItemEntity.getDayOfWeek() == Calendar.SATURDAY -> {
                        calendarDate.setTextColor(Color.BLUE)
                    }
                    calendarItemEntity.getDayOfWeek() == Calendar.SUNDAY -> {
                        calendarDate.setTextColor(Color.RED)
                    }
                    else -> {
                        calendarDate.setTextColor(Color.BLACK)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(
                binding
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(itemList[position], month)
    }

    override fun getItemCount(): Int {
        return repository.getCalendarItemLists().size
    }
}