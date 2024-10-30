package com.example.mytaskboard.di

import android.content.Context
import androidx.work.WorkManager
import com.example.mytaskboard.core.data.DateConverter
import com.example.mytaskboard.core.presentation.MessageLiveDataWrapper
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindRunAsync(runAsync: RunAsync.Base): RunAsync

    @Binds
    abstract fun timeLogConverter(converter: TimeLogToSpentTimeConverter.Base): TimeLogToSpentTimeConverter

    @Binds
    abstract fun dateConverter(converter: DateConverter.Base): DateConverter

    @Binds
    abstract fun messageLiveWrapper(liveDataWrapper: MessageLiveDataWrapper.Base): MessageLiveDataWrapper

    companion object {
        @Provides
        fun workManager(@ApplicationContext appContext: Context): WorkManager =
            WorkManager.getInstance(appContext)
    }
}