package com.example.mytaskboard.taskboard.todo.presentation

import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskUi
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TasksAdapter

interface TasksUiState {

    fun show(adapter: TasksAdapter)

    data class Data(private val tasks: List<TaskUi>) : TasksUiState {
        override fun show(adapter: TasksAdapter) {
            adapter.update(tasks)
        }
    }

     object NoTasks : TasksUiState {
        override fun show(adapter: TasksAdapter) {
            adapter.update(listOf(TaskUi.Empty))
        }
    }

    object Progress : TasksUiState {
        override fun show(adapter: TasksAdapter) {
            adapter.update(listOf(TaskUi.Progress))
        }
    }
}