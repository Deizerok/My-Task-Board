package com.example.mytaskboard.taskboard.done_details

import androidx.fragment.app.Fragment
import com.example.mytaskboard.main.Screen

data class TaskDoneDetailsScreen(
    private val taskId: Int,
) : Screen.Add(TaskDoneDetailsFragment::class.java) {

    override fun fragment(): Fragment {
        return TaskDoneDetailsFragment.newInstance(taskId)
    }
}
