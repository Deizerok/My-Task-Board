package com.example.mytaskboard.taskboard.todo_details.data

import com.example.mytaskboard.taskboard.todo_details.domain.TaskDetailsRepository
import com.example.mytaskboard.taskboard.todo.data.cache.TasksCacheDataSource
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import javax.inject.Inject

class BaseTaskDetailsRepository @Inject constructor(
    private val cacheDataSource: TasksCacheDataSource
) : TaskDetailsRepository {

    override suspend fun task(id: Int) = cacheDataSource.task(id).run {
        TaskItem.Base(
            id = id,
            title = task.title,
            description = task.description,
            timeLog = timeLogs.map {
                TimeLogEntry(
                    date = it.date,
                    hours = it.hours,
                    minutes = it.minutes,
                    seconds = it.seconds
                )
            },
            picture = task.picture
        )
    }

    override suspend fun addSessionTime(
        taskId: Int,
        date: Long,
        hours: Int,
        minutes: Int,
        seconds: Int
    ) {
        cacheDataSource.addSessionTime(taskId, date, hours, minutes, seconds)
    }

    override suspend fun finishTask(id: Int) {
        cacheDataSource.finishedTask(id)
    }

    override suspend fun deleteByTaskId(id: Int) {
        cacheDataSource.deleteByTaskId(id)
    }

    override suspend fun restoreTask(id: Int) {
        cacheDataSource.restoreTask(id)
    }
}