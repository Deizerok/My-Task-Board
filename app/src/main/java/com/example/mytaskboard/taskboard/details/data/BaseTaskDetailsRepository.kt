package com.example.mytaskboard.taskboard.details.data

import com.example.mytaskboard.taskboard.details.domain.TaskDetailsRepository
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

    override suspend fun finishTask(id: Int) {
        cacheDataSource.finishedTask(id)
    }

    override suspend fun deleteByTaskId(id: Int) {
        cacheDataSource.deleteByTaskId(id)
    }
}