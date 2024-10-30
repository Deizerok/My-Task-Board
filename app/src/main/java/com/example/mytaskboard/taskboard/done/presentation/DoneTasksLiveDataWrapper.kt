package com.example.mytaskboard.taskboard.done.presentation

import com.example.mytaskboard.core.presentation.LiveDataWrapper
import com.example.mytaskboard.taskboard.todo.presentation.TasksUiState
import javax.inject.Inject

interface DoneTasksLiveDataWrapper : LiveDataWrapper<TasksUiState> {

    class Base @Inject constructor() : DoneTasksLiveDataWrapper, LiveDataWrapper.Single<TasksUiState>()
}