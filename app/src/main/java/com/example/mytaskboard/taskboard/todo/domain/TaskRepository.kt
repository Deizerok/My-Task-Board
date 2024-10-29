package com.example.mytaskboard.taskboard.todo.domain

interface TaskRepository {

    suspend fun tasks(): List<TaskItem>

    suspend fun task(id: Int): TaskItem
}