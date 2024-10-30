package com.example.mytaskboard.taskboard.todo.data.cache

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithLogs(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "task_id",
        entityColumn = "task_id"
    )
    val timeLogs: List<TimeLogEntryEntity>
)