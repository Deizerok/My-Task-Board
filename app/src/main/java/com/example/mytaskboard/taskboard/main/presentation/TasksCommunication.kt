package com.example.mytaskboard.taskboard.main.presentation

import com.example.mytaskboard.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface TasksCommunication : LiveDataWrapper<TasksUiState> {

    class Base @Inject constructor() : TasksCommunication, LiveDataWrapper.Single<TasksUiState>()
}