package com.example.mytaskboard.taskboard.create.domain

interface CreateTaskRepository {

    suspend fun add(title: String, description: String, time: Int, picture: ByteArray)
}