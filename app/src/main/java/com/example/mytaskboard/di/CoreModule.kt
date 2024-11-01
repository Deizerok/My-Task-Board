package com.example.mytaskboard.di

import android.content.Context
import androidx.work.WorkManager
import com.example.mytaskboard.core.data.DateConverter
import com.example.mytaskboard.core.presentation.ContextUtils
import com.example.mytaskboard.core.presentation.ManageResource
import com.example.mytaskboard.core.presentation.MessageLiveDataWrapper
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.core.presentation.TimeLogToSpentTimeConverter
import com.example.mytaskboard.taskboard.board.LanguageStorage
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun languageStore(languageStorage: LanguageStorage.Base): LanguageStorage

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

        @Provides
        fun manageResource(
            @ApplicationContext appContext: Context,
            languageStorage: LanguageStorage.Base
        ): ManageResource {
            val localeToSwitch = Locale(languageStorage.get().local)
            val localeUpdatedContext = ContextUtils.updateLocale(appContext, localeToSwitch)
            return ManageResource.Base(context = localeUpdatedContext)
        }
    }
}