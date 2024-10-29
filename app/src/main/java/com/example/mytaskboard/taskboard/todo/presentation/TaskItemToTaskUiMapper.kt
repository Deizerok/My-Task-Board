package com.example.mytaskboard.taskboard.todo.presentation

import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskUi
import javax.inject.Inject

class TaskItemToTaskUiMapper @Inject constructor() : TaskItem.Mapper<TaskUi> {
    override fun map(
        id: Int,
        title: String,
        description: String,
        time: Int,
        picture: ByteArray
    ): TaskUi = TaskUi.Base(
        id = id,
        title = title,
        description = description,
        time = time,
        picture = picture
    )

}