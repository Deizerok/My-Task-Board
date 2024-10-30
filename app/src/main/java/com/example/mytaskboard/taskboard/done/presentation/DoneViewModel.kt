package com.example.mytaskboard.taskboard.done.presentation

import com.example.fakestore.core.presentation.ProvideLiveData
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.taskboard.create.presentation.CreateTaskScreen
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsScreen
import com.example.mytaskboard.taskboard.done_details.TaskDoneDetailsScreen
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TaskRepository
import com.example.mytaskboard.taskboard.todo.presentation.TasksLiveDataWrapper
import com.example.mytaskboard.taskboard.todo.presentation.TasksUiState
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskClickActions
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoneViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
    private val repository: TaskRepository,
    private val communication: TasksLiveDataWrapper,
    private val mapper: TaskItem.Mapper<TaskUi>,
    runAsync: RunAsync
) : BaseViewModel(runAsync), TaskClickActions, ProvideLiveData<TasksUiState> {

    override fun liveData() = communication.liveData()

    fun init() {
        runAsync({
            repository.doneTasks()
        }, { taskItems ->
            val uiState = if (taskItems.isEmpty()) {
                TasksUiState.NoTasks
            } else {
                TasksUiState.Data(
                    tasks = taskItems.map {
                        it.map(mapper)
                    })
            }
            communication.updateUi(uiState)
        })
    }

    override fun goToTaskDetails(id: Int) {
        navigation.updateUi(TaskDoneDetailsScreen(id))
    }

    override fun goToCreateTask() {
        navigation.updateUi(CreateTaskScreen)
    }
}