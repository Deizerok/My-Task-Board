package com.example.mytaskboard.di

import com.example.mytaskboard.taskboard.details.data.BaseTaskDetailsRepository
import com.example.mytaskboard.taskboard.details.domain.TaskDetailsRepository
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsLiveDataWrapper
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsUiModel
import com.example.mytaskboard.taskboard.details.presentation.ToTaskDetailsUiModelMapper
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class TaskDetailsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsTaskDetailsRepository(repository: BaseTaskDetailsRepository): TaskDetailsRepository

    @Binds
    @ViewModelScoped
    abstract fun mapper(mapper: ToTaskDetailsUiModelMapper): TaskItem.Mapper<TaskDetailsUiModel>

    @Binds
    @ViewModelScoped
    abstract fun mapperToDoneTaskDetailsUiModelMapper(mapper: ToDoneTaskDetailsUiModelMapper): TaskItem.Mapper<TaskDoneDetailsUiModel>

    @Binds
    @ViewModelScoped
    abstract fun taskDetailsLiveDataWrapper(taskLiveDataWrapper: TaskDetailsLiveDataWrapper.Base): TaskDetailsLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun stopwatchLiveDataWrapper(stopwatchLiveDataWrapper: StopwatchLiveDataWrapper.Base): StopwatchLiveDataWrapper
    abstract fun bindTaskDetailsLiveDataWrapper(liveDataWrapper: TaskDetailsLiveDataWrapper.Base): TaskDetailsLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindTaskDoneDetailsLiveDataWrapper(liveDataWrapper: TaskDoneDetailsLiveDataWrapper.Base): TaskDoneDetailsLiveDataWrapper
}