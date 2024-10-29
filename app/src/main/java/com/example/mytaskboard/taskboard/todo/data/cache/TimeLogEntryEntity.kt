package com.example.mytaskboard.taskboard.todo.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TimeLogEntry",
    foreignKeys = [ForeignKey(
        entity = TaskEntity::class,
        parentColumns = ["task_id"],
        childColumns = ["task_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["task_id"])]
)
data class TimeLogEntryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "log_id")
    val logId: Int = 0,
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    val date: Long,
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
)