package com.example.mytaskboard.taskboard.todo.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface TaskDao {

    @Transaction
    @Query("SELECT * FROM tasks WHERE finished = 0")
    suspend fun todoTasks(): List<TaskWithLogs>
    @Transaction
    @Query("SELECT * FROM tasks WHERE finished = 1")
    suspend fun doneTasks(): List<TaskWithLogs>

    @Query("SELECT * FROM tasks WHERE task_id = :id")
    suspend fun task(id: Int): TaskWithLogs

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE task_id = :id")
    suspend fun deleteByTaskId(id: Int)
}