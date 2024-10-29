package com.example.mytaskboard.di

import com.example.mytaskboard.core.domain.LoadResult
import com.example.mytaskboard.taskboard.board.data.BaseTasksRepository
import com.example.mytaskboard.taskboard.board.domain.TaskItem
import com.example.mytaskboard.taskboard.board.domain.TaskRepository
import com.example.mytaskboard.taskboard.todo.BaseTasksLoadResultMapper
import com.example.mytaskboard.taskboard.todo.TaskItemToTaskUiMapper
import com.example.mytaskboard.taskboard.todo.TasksLiveDataWrapper
import com.example.mytaskboard.taskboard.board.presentation.adapter.TaskUi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class TaskBoardModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsTaskRepository(repository: BaseTasksRepository): TaskRepository

    @Binds
    @ViewModelScoped
    abstract fun bindResultMapper(mapper: BaseTasksLoadResultMapper): LoadResult.Mapper<TaskItem>

    @Binds
    @ViewModelScoped
    abstract fun bindTasksCommunication(communication: TasksLiveDataWrapper.Base): TasksLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindTaskItemToTaskUiMapper(mapper: TaskItemToTaskUiMapper): TaskItem.Mapper<TaskUi>
}