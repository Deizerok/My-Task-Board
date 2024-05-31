package com.example.mytaskboard.taskboard.main.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tasks")
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("task")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("time")
    val time: Int,
    @ColumnInfo("picture")
    val picture: ByteArray
)