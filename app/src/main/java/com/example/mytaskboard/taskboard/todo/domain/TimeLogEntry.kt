package com.example.mytaskboard.taskboard.todo.domain

data class TimeLogEntry(
    val date: Long,
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
)