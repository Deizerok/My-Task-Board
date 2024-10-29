package com.example.mytaskboard.taskboard.todo.presentation.adapter

interface TaskClickActions {

    fun goToTaskDetails(id: Int)

    fun goToCreateTask()
}