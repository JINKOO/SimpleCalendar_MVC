package com.kjk.mvc_sample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.CalendarItemEntity
import com.kjk.mvc_sample.data.CalendarItemModel
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding

class CalendarAdapter(
        private val model: CalendarItemModel
        ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private val calendarItemList: ArrayList<CalendarItemEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
                binding,
                model
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return model.getCalendarItemLists().size
    }

    class ViewHolder(
            private val binding: ItemCalendarDateBinding,
            private val model: CalendarItemModel
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            setListener()
        }

        fun bind() {
            binding.calendarDate.text = model.getCalendarItemLists()[adapterPosition].date.toString()
        }

        private fun setListener() {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v) {
                binding.root -> {
                    Toast.makeText(binding.root.context, binding.calendarDate.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}