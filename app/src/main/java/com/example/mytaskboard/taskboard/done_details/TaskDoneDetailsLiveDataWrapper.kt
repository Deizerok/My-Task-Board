package com.example.mytaskboard.taskboard.done_details


import com.example.mytaskboard.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface TaskDoneDetailsLiveDataWrapper : LiveDataWrapper<TaskDoneDetailsUiModel> {

    class Base @Inject constructor() : TaskDoneDetailsLiveDataWrapper,
        LiveDataWrapper.Single<TaskDoneDetailsUiModel>() {

    }
}