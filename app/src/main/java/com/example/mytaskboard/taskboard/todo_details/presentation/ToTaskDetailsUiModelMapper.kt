package com.example.mytaskboard.taskboard.todo_details.presentation

import com.example.mytaskboard.R
import com.example.mytaskboard.core.data.DateConverter
import com.example.mytaskboard.core.presentation.ManageResource
import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import javax.inject.Inject

class ToTaskDetailsUiModelMapper @Inject constructor(
    private val spentTimeConverter: TimeLogToSpentTimeConverter,
    private val dateConverter: DateConverter,
    private val manageResource: ManageResource.Base,
) : TaskItem.Mapper<TaskTodoDetailsUiModel> {

    override fun map(
        id: Int,
        title: String,
        description: String,
        timeLog: List<TimeLogEntry>,
        picture: ByteArray
    ) = TaskTodoDetailsUiModel(
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
                listOf(TimeLogEntryUi.Base(time = manageResource.string(R.string.no_entries)))
            },
        picture = picture
    )
}