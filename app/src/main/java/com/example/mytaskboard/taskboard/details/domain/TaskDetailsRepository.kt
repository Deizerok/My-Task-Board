package com.example.mytaskboard.taskboard.details.domain

import com.example.mytaskboard.taskboard.main.domain.TaskItem

interface TaskDetailsRepository {

    suspend fun task(id: Int): TaskItem

    suspend fun deleteByTaskId(id: Int)

    suspend fun addTimeForTask(time: Int,id: Int)
}