package com.example.mytaskboard.taskboard.main.data

import com.example.mytaskboard.core.domain.LoadResult
import com.example.mytaskboard.taskboard.main.data.cache.TasksCacheDataSource
import com.example.mytaskboard.taskboard.main.domain.TaskItem
import com.example.mytaskboard.taskboard.main.domain.TaskRepository
import javax.inject.Inject

class BaseTasksRepository @Inject constructor(
    private val tasksCacheDataSource: TasksCacheDataSource
) : TaskRepository {
    override suspend fun tasks(): LoadResult<TaskItem> {
        return try {
            val tasks = tasksCacheDataSource.tasks()
            LoadResult.Success(tasks.map {
                TaskItem.Base(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    time = it.time,
                    picture = it.picture
                )
            })
        } catch (e: Exception) {
            LoadResult.Error("Unknown Exception")
        }
    }


    override suspend fun task(id: Int) = tasksCacheDataSource.task(id).run {
        TaskItem.Base(
            id = id,
            title = title,
            description = description,
            time = time,
            picture = picture
        )
    }
}