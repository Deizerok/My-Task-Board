package com.example.mytaskboard.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytaskboard.taskboard.todo.data.cache.TaskDao
import com.example.mytaskboard.taskboard.todo.data.cache.TaskEntity

@Database(
    version = 1,
    entities = [TaskEntity::class],
    exportSchema = false
)
abstract class MyTaskBoardDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}