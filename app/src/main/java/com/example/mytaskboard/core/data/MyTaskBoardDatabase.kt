package com.example.mytaskboard.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytaskboard.taskboard.main.data.cache.TaskDao
import com.example.mytaskboard.taskboard.main.data.cache.TaskEntity

@Database(
    version = 1,
    entities = [TaskEntity::class],
    exportSchema = false
)
abstract class MyTaskBoardDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}