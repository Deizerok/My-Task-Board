package com.example.mytaskboard.taskboard.details.data

import com.example.mytaskboard.taskboard.details.domain.TaskDetailsRepository
import com.example.mytaskboard.taskboard.board.data.cache.TasksCacheDataSource
import com.example.mytaskboard.taskboard.board.domain.TaskItem
import javax.inject.Inject

class BaseTaskDetailsRepository @Inject constructor(
    private val cacheDataSource: TasksCacheDataSource
) : TaskDetailsRepository {

    override suspend fun task(id: Int) = cacheDataSource.task(id).run {
        TaskItem.Base(
            id = id,
            title = title,
            description = description,
            time = time,
            picture = picture
        )
    }


    override suspend fun deleteByTaskId(id: Int) {
        cacheDataSource.deleteByTaskId(id)
    }

    override suspend fun addTimeForTask(time: Int,id: Int) {
        cacheDataSource.addTimeForTask(time, id)
    }
}