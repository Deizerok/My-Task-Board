package com.example.mytaskboard.taskboard.todo.presentation

import com.example.fakestore.core.presentation.ProvideLiveData
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.taskboard.create.presentation.CreateTaskScreen
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.domain.TaskRepository
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskClickActions
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TaskUi
import com.example.mytaskboard.taskboard.todo_details.presentation.TaskTodoDetailsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
    private val repository: TaskRepository,
    private val communication: TasksLiveDataWrapper,
    private val mapper: TaskItem.Mapper<TaskUi>,
    runAsync: RunAsync
) : BaseViewModel(runAsync), TaskClickActions, ProvideLiveData<TasksUiState> {

    override fun liveData() = communication.liveData()

    fun init() {
        runAsync({
            repository.todoTasks()
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
        navigation.updateUi(TaskTodoDetailsScreen(id))
    }

    override fun goToCreateTask() {
        navigation.updateUi(CreateTaskScreen)
    }
}