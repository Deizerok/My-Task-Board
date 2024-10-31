package com.example.mytaskboard.taskboard.board

import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.menu.MenuScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
    private val languageStorage: LanguageStorage,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun goToMenu() {
        navigation.updateUi(MenuScreen)
    }

    fun changeLanguage(language: Language) {
        languageStorage.save(language)
    }

    fun currentLanguage() = languageStorage.get()
}