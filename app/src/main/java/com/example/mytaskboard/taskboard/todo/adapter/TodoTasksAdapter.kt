package com.example.mytaskboard.taskboard.board.presentation.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoTasksAdapter(
    private val viewModel: TaskClickActions,
    private val types: List<TaskTypeUi> = listOf(
        TaskTypeUi.Base, TaskTypeUi.Progress, TaskTypeUi.Empty
    )
) : RecyclerView.Adapter<TodoTaskViewHolder>() {

    private val products = mutableListOf<TaskUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(newListProducts: List<TaskUi>) {
        products.clear()
        products.addAll(newListProducts)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = types.indexOf(products[position].type())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoTaskViewHolder =
        types[viewType].createViewHolder(parent)


    override fun onBindViewHolder(holder: TodoTaskViewHolder, position: Int) {
        holder.bind(products[position], viewModel, this)
    }

    override fun getItemCount(): Int = products.size

    fun notify(productUi: TaskUi) {
        val index = products.indexOf(products.find { it.isTheSameById(productUi.id()) }!!)
        products[index] = productUi
        notifyItemChanged(index)
    }
}