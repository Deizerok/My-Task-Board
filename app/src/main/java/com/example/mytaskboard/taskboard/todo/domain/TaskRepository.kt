package com.example.mytaskboard.taskboard.todo.domain

interface TaskRepository {

    suspend fun todoTasks(): List<TaskItem>

    suspend fun task(id: Int): TaskItem
    suspend fun doneTasks(): List<TaskItem>
}