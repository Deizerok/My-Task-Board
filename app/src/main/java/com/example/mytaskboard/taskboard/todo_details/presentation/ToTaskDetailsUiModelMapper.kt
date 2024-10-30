package com.example.mytaskboard.taskboard.todo_details.presentation

import com.example.mytaskboard.core.data.DateConverter
import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import javax.inject.Inject

class ToTaskDetailsUiModelMapper @Inject constructor(
    private val spentTimeConverter: TimeLogToSpentTimeConverter,
    private val dateConverter: DateConverter
) : TaskItem.Mapper<TaskDetailsUiModel> {

    override fun map(
        id: Int,
        title: String,
        description: String,
        timeLog: List<TimeLogEntry>,
        picture: ByteArray
    ) = TaskDetailsUiModel(
        id = id,
        title = title,
        description = description,
        spentTime = spentTimeConverter.convert(timeLog),
        timeLog = timeLog
            .map {
                val date = dateConverter.convertToLocal(it.date)
                val spentTime = StringBuilder()
                if (it.hours != 0) {
                    spentTime.append(it.hours).append("h ")
                }
                if (it.minutes != 0) {
                    spentTime.append(it.minutes).append("m ")
                }
                if (it.seconds != 0) {
                    spentTime.append(it.seconds).append("s")
                }
                TimeLogEntryUi.Base(time = "$date    + $spentTime")
            }
            .ifEmpty {
                listOf(TimeLogEntryUi.NoEntries)
            },
        picture = picture
    )
}