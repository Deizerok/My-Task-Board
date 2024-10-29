package com.example.mytaskboard.taskboard.todo.presentation

import com.example.mytaskboard.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface TasksLiveDataWrapper : LiveDataWrapper<TasksUiState> {

    class Base @Inject constructor() : TasksLiveDataWrapper, LiveDataWrapper.Single<TasksUiState>()
}