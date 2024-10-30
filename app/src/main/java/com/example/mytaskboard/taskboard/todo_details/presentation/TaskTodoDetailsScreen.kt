package com.example.mytaskboard.taskboard.todo_details.presentation

import androidx.fragment.app.Fragment
import com.example.mytaskboard.main.Screen

data class TaskTodoDetailsScreen(
    private val taskId: Int,
) : Screen.Add(TaskTodoDetailsFragment::class.java) {

    override fun fragment(): Fragment {
        return TaskTodoDetailsFragment.newInstance(taskId)
    }
}
