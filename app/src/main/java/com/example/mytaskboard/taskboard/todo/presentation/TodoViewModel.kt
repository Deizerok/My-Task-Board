package com.example.mytaskboard.taskboard.todo.presentation

import com.example.fakestore.core.presentation.ProvideLiveData
import com.example.mytaskboard.core.domain.LoadResult
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.taskboard.create.presentation.CreateTaskScreen
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsScreen
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TaskRepository
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskClickActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
    private val repository: TaskRepository,
    private val communication: TasksLiveDataWrapper,
    private val mapper: LoadResult.Mapper<TaskItem>,
    runAsync: RunAsync
) : BaseViewModel(runAsync), TaskClickActions, ProvideLiveData<TasksUiState> {

    override fun liveData() = communication.liveData()

    fun init() {
        runAsync({
            repository.tasks()
        }, { result ->
            result.map(mapper)
        })
    }

    override fun goToTaskDetails(id: Int) {
        navigation.updateUi(TaskDetailsScreen(id))
    }

    override fun goToCreateTask() {
        navigation.updateUi(CreateTaskScreen)
    }

}