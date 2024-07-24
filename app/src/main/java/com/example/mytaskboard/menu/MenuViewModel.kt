package com.example.mytaskboard.menu

import com.example.fakestore.core.presentation.ProvideLiveData
import com.example.mytaskboard.core.domain.LoadResult
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.main.Screen
import com.example.mytaskboard.taskboard.create.presentation.CreateTaskScreen
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsScreen
import com.example.mytaskboard.taskboard.main.domain.TaskItem
import com.example.mytaskboard.taskboard.main.domain.TaskRepository
import com.example.mytaskboard.taskboard.main.presentation.adapter.TaskClickActions
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