package com.example.mytaskboard.taskboard.done_details

import com.example.mytaskboard.main.Screen
import com.example.mytaskboard.taskboard.details.presentation.BottomSheetFinishTaskFragment

data class BottomSheetDeleteTaskScreen(
    private val taskId: Int,
) : Screen.BottomSheet(BottomSheetDeleteTaskFragment::class.java) {

    override fun fragment() = BottomSheetFinishTaskFragment.newInstance(taskId)
}
