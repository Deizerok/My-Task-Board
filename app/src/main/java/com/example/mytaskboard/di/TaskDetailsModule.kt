package com.example.mytaskboard.di

import com.example.mytaskboard.taskboard.done_details.TaskDoneDetailsLiveDataWrapper
import com.example.mytaskboard.taskboard.done_details.TaskDoneDetailsUiModel
import com.example.mytaskboard.taskboard.done_details.ToDoneTaskDetailsUiModelMapper
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo_details.data.BaseTaskDetailsRepository
import com.example.mytaskboard.taskboard.todo_details.domain.TaskDetailsRepository
import com.example.mytaskboard.taskboard.todo_details.presentation.StopwatchLiveDataWrapper
import com.example.mytaskboard.taskboard.todo_details.presentation.TaskTodoDetailsLiveDataWrapper
import com.example.mytaskboard.taskboard.todo_details.presentation.TaskTodoDetailsUiModel
import com.example.mytaskboard.taskboard.todo_details.presentation.ToTaskDetailsUiModelMapper
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
    abstract fun mapper(mapper: ToTaskDetailsUiModelMapper): TaskItem.Mapper<TaskTodoDetailsUiModel>

    @Binds
    @ViewModelScoped
    abstract fun mapperToDoneTaskDetailsUiModelMapper(mapper: ToDoneTaskDetailsUiModelMapper): TaskItem.Mapper<TaskDoneDetailsUiModel>

    @Binds
    @ViewModelScoped
    abstract fun taskDetailsLiveDataWrapper(taskLiveDataWrapper: TaskTodoDetailsLiveDataWrapper.Base): TaskTodoDetailsLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun stopwatchLiveDataWrapper(stopwatchLiveDataWrapper: StopwatchLiveDataWrapper.Base): StopwatchLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindTaskDoneDetailsLiveDataWrapper(liveDataWrapper: TaskDoneDetailsLiveDataWrapper.Base): TaskDoneDetailsLiveDataWrapper
}