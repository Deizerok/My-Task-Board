package com.example.mytaskboard.taskboard.todo_details.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytaskboard.databinding.ViewholderTimeLogEntryBinding
import com.example.mytaskboard.taskboard.todo_details.presentation.TimeLogEntryUi

class TimeLogAdapter() : RecyclerView.Adapter<TimeLogAdapter.TimeLogEntryViewHolder>() {

    private val timeLog = mutableListOf<TimeLogEntryUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(newTimeLog: List<TimeLogEntryUi>) {
        timeLog.clear()
        timeLog.addAll(newTimeLog)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TimeLogEntryViewHolder(
        ViewholderTimeLogEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = timeLog.size

    override fun onBindViewHolder(holder: TimeLogEntryViewHolder, position: Int) {
        holder.bind(time = timeLog[position].time())
    }

    class TimeLogEntryViewHolder(
        private val binding: ViewholderTimeLogEntryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(time: String) {
            binding.timeLogEntryTextView.text = time
        }
    }
}

