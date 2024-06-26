package com.example.mytaskboard.taskboard.main.data.cache


import javax.inject.Inject

interface TasksCacheDataSource {

    suspend fun tasks(): List<TaskEntity>

    suspend fun task(id: Int): TaskEntity

    suspend fun addTask(task: TaskEntity)

    suspend fun addTimeForTask(time: Int,id: Int)

    class Base @Inject constructor(
        private val dao: TaskDao,
    ) : TasksCacheDataSource {

        override suspend fun tasks(): List<TaskEntity> = dao.tasks()

        override suspend fun task(id: Int): TaskEntity = dao.task(id)

        override suspend fun addTask(task: TaskEntity) = dao.addTask(task)

        override suspend fun addTimeForTask(time: Int,id: Int) {
            val taskEntity: TaskEntity = dao.task(id)
            val newTaskEntity = taskEntity.copy(time = time)
            dao.addTask(newTaskEntity)
        }
    }
}

