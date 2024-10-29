package com.example.mytaskboard.di

import android.content.Context
import androidx.room.Room
import com.example.mytaskboard.core.data.MyTaskBoardDatabase
import com.example.mytaskboard.taskboard.board.data.cache.TaskDao
import com.example.mytaskboard.taskboard.board.data.cache.TasksCacheDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyTaskBoardDatabase = Room
        .databaseBuilder(
            context,
            MyTaskBoardDatabase::class.java,
            "my_task_board_db"
        ).build()

    @Provides
    fun provideTasksDao(database: MyTaskBoardDatabase): TaskDao = database.taskDao()

    @Provides
    fun provideTasksCacheDataSource(database: MyTaskBoardDatabase): TasksCacheDataSource =
        TasksCacheDataSource.Base(database.taskDao())
}