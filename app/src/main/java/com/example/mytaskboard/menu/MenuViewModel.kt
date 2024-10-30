package com.example.mytaskboard.menu

import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.main.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {


    fun goToBackScreen() {
        navigation.updateUi(Screen.Pop)
    }


}