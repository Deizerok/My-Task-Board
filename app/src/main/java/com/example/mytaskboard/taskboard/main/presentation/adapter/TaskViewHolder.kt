package com.example.mytaskboard.taskboard.main.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.mytaskboard.databinding.ViewholderNoTaskBinding
import com.example.mytaskboard.databinding.ViewholderProgressBinding
import com.example.mytaskboard.databinding.ViewholderTaskBinding

abstract class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(
        productUi: TaskUi,
        viewModel: TaskClickActions,
        adapter: TasksAdapter
    ) = Unit


    class Progress(binding: ViewholderProgressBinding) : TaskViewHolder(binding.root)

    class Base(private val binding: ViewholderTaskBinding) : TaskViewHolder(binding.root) {

        override fun bind(
            productUi: TaskUi,
            viewModel: TaskClickActions,
            adapter: TasksAdapter
        ) {
            binding.taskViewHolder.setOnClickListener {
                productUi.goToTaskDetails(viewModel)
            }

            productUi.showTask(binding, adapter)
        }
    }
    class Empty(binding: ViewholderNoTaskBinding) : TaskViewHolder(binding.root)

}
