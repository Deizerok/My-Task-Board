package com.example.mytaskboard.di

import com.example.mytaskboard.taskboard.create.data.BaseCreateTaskRepository
import com.example.mytaskboard.taskboard.create.domain.CreateTaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class CreateTaskModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsCreateTaskRepository(repository: BaseCreateTaskRepository): CreateTaskRepository
}