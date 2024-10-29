package com.example.mytaskboard.taskboard.board.presentation.adapter

interface TaskClickActions {

    fun goToTaskDetails(id: Int)

    fun goToCreateTask()
}