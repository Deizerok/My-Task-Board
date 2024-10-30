package com.example.mytaskboard.taskboard.done_details

import com.example.mytaskboard.main.Screen

data class BottomSheetRestoreTaskScreen(
    private val taskId: Int,
) : Screen.BottomSheet(BottomSheetRestoreTaskFragment::class.java) {

    override fun fragment() = BottomSheetRestoreTaskFragment.newInstance(taskId)
}
