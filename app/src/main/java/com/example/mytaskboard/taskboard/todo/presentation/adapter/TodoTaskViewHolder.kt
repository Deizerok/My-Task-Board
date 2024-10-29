package com.example.mytaskboard.taskboard.todo.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mytaskboard.databinding.ViewholderNoTaskBinding
import com.example.mytaskboard.databinding.ViewholderProgressBinding
import com.example.mytaskboard.databinding.ViewholderTaskBinding

abstract class TodoTaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(
        productUi: TaskUi,
        viewModel: TaskClickActions,
        adapter: TodoTasksAdapter
    ) = Unit


    class Progress(binding: ViewholderProgressBinding) : TodoTaskViewHolder(binding.root)

    class Base(private val binding: ViewholderTaskBinding) : TodoTaskViewHolder(binding.root) {

        override fun bind(
            productUi: TaskUi,
            viewModel: TaskClickActions,
            adapter: TodoTasksAdapter
        ) {
            binding.taskViewHolder.setOnClickListener {
                productUi.goToTaskDetails(viewModel)
            }

            productUi.showTask(binding, adapter)
        }
    }

    class Empty(binding: ViewholderNoTaskBinding) : TodoTaskViewHolder(binding.root)

}
