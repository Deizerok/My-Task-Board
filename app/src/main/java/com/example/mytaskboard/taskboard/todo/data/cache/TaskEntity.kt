package com.example.mytaskboard.taskboard.todo.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("task_id")
    val id: Int = 0,
    val title: String,
    val description: String,
    val picture: ByteArray,
    val finished: Byte = 0
)