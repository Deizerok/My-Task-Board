package com.example.mytaskboard.taskboard.details.domain

import com.example.mytaskboard.taskboard.todo.domain.TaskItem

interface TaskDetailsRepository {

    suspend fun task(id: Int): TaskItem

    suspend fun addSessionTime(taskId: Int, date: Long, hours: Int, minutes: Int, seconds: Int)

    suspend fun finishTask(id: Int)

    suspend fun deleteByTaskId(id: Int)
}