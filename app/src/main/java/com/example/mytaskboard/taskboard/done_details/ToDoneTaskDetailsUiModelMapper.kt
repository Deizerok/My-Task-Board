package com.example.mytaskboard.taskboard.done_details

import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import javax.inject.Inject

class ToDoneTaskDetailsUiModelMapper @Inject constructor(
    private val converter: TimeLogToSpentTimeConverter
) : TaskItem.Mapper<TaskDoneDetailsUiModel> {

    override fun map(
        id: Int,
        title: String,
        description: String,
        timeLog: List<TimeLogEntry>,
        picture: ByteArray
    ): TaskDoneDetailsUiModel = TaskDoneDetailsUiModel(
        id = id,
        title = title,
        description = description,
        spentTime = converter.convert(timeLog),
        timeLog = timeLog,
        picture = picture
    )
}