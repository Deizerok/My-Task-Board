package com.example.mytaskboard.taskboard.todo_details.presentation


import com.example.mytaskboard.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface TaskTodoDetailsLiveDataWrapper : LiveDataWrapper<TaskTodoDetailsUiModel> {

    class Base @Inject constructor() : TaskTodoDetailsLiveDataWrapper,
        LiveDataWrapper.Single<TaskTodoDetailsUiModel>()
}