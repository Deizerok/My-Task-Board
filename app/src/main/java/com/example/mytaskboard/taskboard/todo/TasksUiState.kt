package com.example.mytaskboard.taskboard.todo

import com.example.mytaskboard.taskboard.board.presentation.adapter.TaskUi
import com.example.mytaskboard.taskboard.board.presentation.adapter.TodoTasksAdapter

interface TasksUiState {

    fun show(adapter: TodoTasksAdapter)

    data class Success(private val tasks: List<TaskUi>) : TasksUiState {
        override fun show(adapter: TodoTasksAdapter) {
            adapter.update(tasks)
        }
    }

     object NoTasks : TasksUiState {
        override fun show(adapter: TodoTasksAdapter) {
            adapter.update(listOf(TaskUi.Empty))
        }
    }

    object Progress : TasksUiState {
        override fun show(adapter: TodoTasksAdapter) {
            adapter.update(listOf(TaskUi.Progress))
        }
    }
}