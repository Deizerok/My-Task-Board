package com.example.mytaskboard.taskboard.todo.presentation.adapter

import android.graphics.BitmapFactory
import com.example.mytaskboard.databinding.ViewholderTaskBinding

interface TaskUi {

    fun id(): Int = -1

    fun type(): TaskTypeUi

    fun showTask(binding: ViewholderTaskBinding, adapter: TodoTasksAdapter) = Unit

    fun goToTaskDetails(viewModel: TaskClickActions) = Unit

    fun isTheSameById(id: Int): Boolean = false

    data class Base(
        private val id: Int,
        private var title: String,
        private val timeSpent: String,
        private val picture: ByteArray
    ) : TaskUi {

        override fun id() = id

        override fun type() = TaskTypeUi.Base

        override fun goToTaskDetails(viewModel: TaskClickActions) {
            viewModel.goToTaskDetails(id = id)
        }

        override fun showTask(binding: ViewholderTaskBinding, adapter: TodoTasksAdapter) {
            val bitmap = BitmapFactory.decodeByteArray(
                picture, 0, picture.size
            )
            with(binding) {
                titleTextView.text = title
                timeTextView.text = timeSpent
                iconImageView.setImageBitmap(bitmap)
            }
        }

        override fun isTheSameById(id: Int) = this.id == id
    }


    object Empty : TaskUi {
        override fun type(): TaskTypeUi = TaskTypeUi.Empty
    }
}