package com.example.mytaskboard.taskboard.todo.data.cache


import javax.inject.Inject

interface TasksCacheDataSource {

    suspend fun tasks(): List<TaskEntity>

    suspend fun task(id: Int): TaskEntity

    suspend fun addTask(task: TaskEntity)

    suspend fun deleteByTaskId(id: Int)

    suspend fun addTimeForTask(time: Int,id: Int)

    class Base @Inject constructor(
        private val dao: TaskDao,
    ) : TasksCacheDataSource {

        override suspend fun tasks(): List<TaskEntity> = dao.tasks()

        override suspend fun task(id: Int): TaskEntity = dao.task(id)

        override suspend fun addTask(task: TaskEntity) = dao.addTask(task)

        override suspend fun deleteByTaskId(id: Int) {
            dao.deleteByTaskId(id)
        }

        override suspend fun addTimeForTask(time: Int,id: Int) {
            val taskEntity: TaskEntity = dao.task(id)
            val timeEntity = taskEntity.time
            val newTaskEntity = taskEntity.copy(time = timeEntity + time)
            dao.addTask(newTaskEntity)
        }
    }
}

