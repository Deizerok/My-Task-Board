package com.example.mytaskboard.main

import androidx.lifecycle.LiveData
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.splash.presentation.SplashScreen
import com.example.mytaskboard.taskboard.board.presentation.TaskBoardScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun navigationLiveData(): LiveData<Screen> = navigation.liveData()

    fun init(firstRun: Boolean) {
        if (firstRun) {
            navigation.updateUi(SplashScreen)
        }
    }

    fun startTaskBoard() = runAsync({
        delay(4000)
    }, {
        navigation.updateUi(TaskBoardScreen)
    })
}


