package com.example.mytaskboard.taskboard.details.presentation

import android.graphics.BitmapFactory
import com.example.mytaskboard.databinding.FragmentTaskDetailsBinding

class TaskDetailsUiModel(
    private val id: Int,
    private var title: String,
    private val time: Int,
    private val description: String,
    private val picture: ByteArray
) {

    fun show(binding: FragmentTaskDetailsBinding) {
        val bitmap = BitmapFactory.decodeByteArray(
            picture, 0, picture.size
        )
        binding.iconImageView.setImageBitmap(bitmap)
        binding.titleTextView.text = title
        binding.descriptionTextView.text = description
        binding.timeTextView.text = "$time m"
    }
}
