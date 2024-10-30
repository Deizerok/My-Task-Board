package com.example.mytaskboard.taskboard.details.presentation

import com.example.mytaskboard.main.Screen

data class BottomSheetFinishTaskScreen(
    private val taskId: Int,
) : Screen.BottomSheet(BottomSheetFinishTaskFragment::class.java) {

    override fun fragment() = BottomSheetFinishTaskFragment.newInstance(taskId)
}
