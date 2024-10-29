package com.example.mytaskboard.di

import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindRunAsync(runAsync: RunAsync.Base): RunAsync

    @Binds
    abstract fun converter(converter: TimeLogToSpentTimeConverter.Base): TimeLogToSpentTimeConverter
}