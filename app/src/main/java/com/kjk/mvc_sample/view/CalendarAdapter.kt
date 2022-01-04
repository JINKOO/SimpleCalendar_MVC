package com.kjk.mvc_sample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjk.mvc_sample.data.CalendarDataSender
import com.kjk.mvc_sample.databinding.ItemCalendarDateBinding


class CalendarAdapter(
        private val sender : CalendarDataSender
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding, sender)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(sender.getItemList()[position], sender.getBaseDate().monthValue)
    }

    override fun getItemCount(): Int {
        return sender.getItemList().size
    }

}