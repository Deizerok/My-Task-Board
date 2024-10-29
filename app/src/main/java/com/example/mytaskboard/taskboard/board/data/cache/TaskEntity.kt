package com.example.mytaskboard.taskboard.board.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int = 0,
    @ColumnInfo("task")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("time")
    val time: Int,
    @ColumnInfo("picture")
    val picture: ByteArray
)