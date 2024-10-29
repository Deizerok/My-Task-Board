package com.example.mytaskboard.taskboard.create.presentation

import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.main.Screen
import com.example.mytaskboard.taskboard.create.domain.CreateTaskRepository
import com.example.mytaskboard.taskboard.board.presentation.TaskBoardScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
    private val repository: CreateTaskRepository,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun createTask(title: String, description: String, time: Int, picture: ByteArray) {
        runAsync({
                repository.add(title, description, time, picture)
        }, { result ->
            navigation.updateUi(TaskBoardScreen)
        })
    }

    fun back() {
        navigation.updateUi(Screen.Pop)
    }

}