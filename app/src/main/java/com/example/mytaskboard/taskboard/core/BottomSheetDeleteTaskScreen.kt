package com.example.mytaskboard.taskboard.core

import com.example.mytaskboard.main.Screen

data class BottomSheetDeleteTaskScreen(
    private val taskId: Int,
) : Screen.BottomSheet(BottomSheetDeleteTaskFragment::class.java) {

    override fun fragment() = BottomSheetDeleteTaskFragment.newInstance(taskId)
}
