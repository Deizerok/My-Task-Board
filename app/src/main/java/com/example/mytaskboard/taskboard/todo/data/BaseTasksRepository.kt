package com.example.mytaskboard.taskboard.todo.data

import com.example.mytaskboard.taskboard.todo.data.cache.TasksCacheDataSource
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TaskRepository
import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import javax.inject.Inject

class BaseTasksRepository @Inject constructor(
    private val tasksCacheDataSource: TasksCacheDataSource
) : TaskRepository {

    override suspend  fun tasks(): List<TaskItem> {
        return tasksCacheDataSource.tasks().map {
            TaskItem.Base(
                id = it.task.id,
                title = it.task.title,
                description = it.task.description,
                timeLog = it.timeLogs.map { timeLog ->
                    TimeLogEntry(
                        date = timeLog.date,
                        hours = timeLog.hours,
                        minutes = timeLog.minutes,
                        seconds = timeLog.seconds
                    )
                },
                picture = it.task.picture
            )
        }
    }

    override suspend fun task(id: Int) = tasksCacheDataSource.task(id).run {
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
}