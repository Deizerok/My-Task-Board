package com.example.mytaskboard.taskboard.todo.data.cache


import javax.inject.Inject

interface TasksCacheDataSource {

    suspend fun tasks(): List<TaskWithLogs>

    suspend fun task(id: Int): TaskWithLogs

    suspend fun addTask(task: TaskEntity)

    suspend fun addSessionTime(taskId: Int, date: Long, hours: Int, minutes: Int, seconds: Int)

    suspend fun finishedTask(id: Int)

    suspend fun deleteByTaskId(id: Int)

    class Base @Inject constructor(private val dao: TaskDao) : TasksCacheDataSource {

        override suspend fun tasks(): List<TaskWithLogs> = dao.todoTasks()

        override suspend fun task(id: Int): TaskWithLogs = dao.task(id)

        override suspend fun addTask(task: TaskEntity) = dao.addTask(task)

        override suspend fun addSessionTime(
            taskId: Int,
            date: Long,
            hours: Int,
            minutes: Int,
            seconds: Int
        ) {
            dao.addTimeLogEntry(
                TimeLogEntryEntity(
                    taskId = taskId,
                    date = date,
                    hours = hours,
                    minutes = minutes,
                    seconds = seconds
                )
            )
        }

        override suspend fun finishedTask(id: Int) {
            val taskEntity = dao.task(id)
            val finishedTask = taskEntity.task.copy(finished = 1)
            dao.updateTask(finishedTask)
        }

        override suspend fun deleteByTaskId(id: Int) {
            dao.deleteByTaskId(id)
        }
    }
}

