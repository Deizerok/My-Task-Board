package com.example.mytaskboard.taskboard.todo.presentation

import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskUi
import javax.inject.Inject

class TaskItemToTaskUiMapper @Inject constructor(
    private val converter: TimeLogToSpentTimeConverter
) : TaskItem.Mapper<TaskUi> {

    override fun map(
        id: Int,
        title: String,
        description: String,
        timeLog: List<TimeLogEntry>,
        picture: ByteArray
    ): TaskUi = TaskUi.Base(
        id = id,
        title = title,
        timeSpent = converter.convert(timeLog),
        picture = picture
    )
}