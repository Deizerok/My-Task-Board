package com.example.mytaskboard.taskboard.todo_details.presentation

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.View
import com.example.mytaskboard.databinding.FragmentTaskDetailsBinding
import com.example.mytaskboard.taskboard.todo_details.presentation.adapter.TimeLogAdapter

data class TaskDetailsUiModel(
    private val id: Int,
    private var title: String,
    private val description: String,
    private val spentTime: String,
    private val timeLog: List<TimeLogEntryUi>,
    private val picture: ByteArray
) {

    fun getTitle() = title

    @SuppressLint("SetTextI18n")
    fun show(binding: FragmentTaskDetailsBinding) = with(binding) {

        val bitmap = BitmapFactory.decodeByteArray(
            picture, 0, picture.size
        )
        iconImageView.setImageBitmap(bitmap)
        titleTextView.text = title
        if (description == "") {
            descriptionLinearLayout.visibility = View.GONE
        } else {
            descriptionTextView.text = description
        }

        // Time log
        totalTimeTextView.text = spentTime
        (timeLogRecyclerView.adapter as TimeLogAdapter).update(timeLog)
    }
}