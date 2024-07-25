package com.example.mytaskboard.taskboard.create.data

import androidx.room.Insert
import com.example.mytaskboard.taskboard.create.domain.CreateTaskRepository
import com.example.mytaskboard.taskboard.main.data.cache.TaskEntity
import com.example.mytaskboard.taskboard.main.data.cache.TasksCacheDataSource
import javax.inject.Inject

class BaseCreateTaskRepository @Inject constructor(
    private val tasksCacheDataSource: TasksCacheDataSource
): CreateTaskRepository {
    override suspend fun add(title: String, description: String, time: Int, picture: ByteArray) {
        val task = TaskEntity(
            title = title,
            description = description,
            time = time,
            picture = picture
        )
        tasksCacheDataSource.addTask(task)
    }
}