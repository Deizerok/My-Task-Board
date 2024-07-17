package com.example.mytaskboard.taskboard.details.presentation

import androidx.fragment.app.Fragment
import com.example.mytaskboard.main.Screen

data class TaskDetailsScreen(
    private val taskId: Int,
) : Screen.Add(TaskDetailsFragment::class.java) {

    override fun fragment(): Fragment {
        return TaskDetailsFragment.newInstance(taskId)
    }
}
