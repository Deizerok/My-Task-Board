package com.example.mytaskboard.taskboard.todo_details.presentation


import com.example.mytaskboard.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface TaskDetailsLiveDataWrapper : LiveDataWrapper<TaskDetailsUiModel> {

    class Base @Inject constructor() : TaskDetailsLiveDataWrapper,
        LiveDataWrapper.Single<TaskDetailsUiModel>()
}