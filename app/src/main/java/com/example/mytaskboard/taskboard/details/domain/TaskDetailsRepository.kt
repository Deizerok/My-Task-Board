package com.example.mytaskboard.taskboard.details.domain

import com.example.mytaskboard.taskboard.todo.domain.TaskItem

interface TaskDetailsRepository {

    suspend fun task(id: Int): TaskItem

    suspend fun finishTask(id: Int)

    suspend fun deleteByTaskId(id: Int)

    suspend fun restoreTask(id: Int)
}