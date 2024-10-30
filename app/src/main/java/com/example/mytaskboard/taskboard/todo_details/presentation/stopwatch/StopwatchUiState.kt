package com.example.mytaskboard.taskboard.todo_details.presentation.stopwatch


import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentTaskDetailsBinding

interface StopwatchUiState {

    fun show(binding: FragmentTaskDetailsBinding)

    object Initial : StopwatchUiState {
        override fun show(binding: FragmentTaskDetailsBinding) {
            binding.sessionTimeTextView.text = "00:00:00"
            binding.actionButtonTextView.text =
                binding.actionButtonTextView.context.getString(R.string.start_session)
        }
    }

    data class Time(val value: String) : StopwatchUiState {
        override fun show(binding: FragmentTaskDetailsBinding) {
            binding.sessionTimeTextView.text = value
            binding.actionButtonTextView.text =
                binding.actionButtonTextView.context.getString(R.string.stop_session)
        }
    }
}