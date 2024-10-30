package com.example.mytaskboard.taskboard.todo.presentation

import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskUi
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TodoTasksAdapter

interface TasksUiState {

    fun show(adapter: TodoTasksAdapter)

    data class Data(private val tasks: List<TaskUi>) : TasksUiState {
        override fun show(adapter: TodoTasksAdapter) {
            adapter.update(tasks)
        }
    }

     object NoTasks : TasksUiState {
         override fun show(adapter: TodoTasksAdapter) {
            adapter.update(listOf(TaskUi.Empty))
        }
    }
}