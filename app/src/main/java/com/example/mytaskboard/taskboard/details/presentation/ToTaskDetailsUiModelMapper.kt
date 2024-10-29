package com.example.mytaskboard.taskboard.details.presentation

import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import javax.inject.Inject

class ToTaskDetailsUiModelMapper @Inject constructor(
    private val converter: TimeLogToSpentTimeConverter
) : TaskItem.Mapper<TaskDetailsUiModel> {

    override fun map(
        id: Int,
        title: String,
        description: String,
        timeLog: List<TimeLogEntry>,
        picture: ByteArray
    ): TaskDetailsUiModel = TaskDetailsUiModel(
        id = id,
        title = title,
        description = description,
        spentTime = converter.convert(timeLog),
        timeLog = timeLog,
        picture = picture
    )
}