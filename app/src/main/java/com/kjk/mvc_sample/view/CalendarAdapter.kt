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
        //Todo : Sender를 넘기는데,
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(sender.getItemList()[position], sender.getBaseDate().monthValue)
        //Todo : 여기서 sender.getItemList()[position] / sender.getBaseDate().monthValue 를 넘 필요가 있을까요?
        //Todo : Position만 넘기면 될것 같습니다.
    }

    override fun getItemCount(): Int {
        return sender.getItemList().size
    }

}