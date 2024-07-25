package com.example.mytaskboard.taskboard.main.domain

import com.example.mytaskboard.core.domain.LoadResult

interface TaskRepository {

    suspend fun tasks(): LoadResult<TaskItem>

    suspend fun task(id: Int): TaskItem
}