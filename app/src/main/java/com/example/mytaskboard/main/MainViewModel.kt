package com.example.mytaskboard.main

import androidx.lifecycle.LiveData
import com.example.mytaskboard.core.data.AuthRepository
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.login.LoginScreen
import com.example.mytaskboard.splash.SplashScreen
import com.example.mytaskboard.taskboard.board.TaskBoardScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable,
    private val authRepository: AuthRepository,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun navigationLiveData(): LiveData<Screen> = navigation.liveData()

    fun init(firstRun: Boolean) {
        if (firstRun) {
            navigation.updateUi(SplashScreen)
        }
    }

    fun checkUserSigning() = runAsync({
        delay(2000)
        if(authRepository.isUserLogged())  TaskBoardScreen else LoginScreen

    }, { screen->
        navigation.updateUi(screen)
    })
}


