package com.example.mytaskboard.taskboard.create.domain

import com.example.mytaskboard.taskboard.main.data.cache.TaskEntity

interface CreateTaskRepository {

    suspend fun add(title: String, description: String, time: Int, picture: ByteArray)
}