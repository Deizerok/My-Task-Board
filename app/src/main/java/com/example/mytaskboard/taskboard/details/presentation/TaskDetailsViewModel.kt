package com.example.mytaskboard.taskboard.details.presentation

import androidx.lifecycle.LiveData
import com.example.fakestore.core.presentation.ProvideLiveData
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.taskboard.details.domain.TaskDetailsRepository
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.board.presentation.TaskBoardScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val liveDataWrapper: TaskDetailsLiveDataWrapper,
    private val navigation: Navigation.Navigate,
    private val repository: TaskDetailsRepository,
    private val mapper: TaskItem.Mapper<TaskDetailsUiModel>,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<TaskDetailsUiModel> {

    override fun liveData(): LiveData<TaskDetailsUiModel> = liveDataWrapper.liveData()

    fun init(id: Int) {
        runAsync({
            repository.task(id)
        }, { result ->
            liveDataWrapper.updateUi(result.map(mapper))
        })
    }

    fun back() = navigation.updateUi(TaskBoardScreen)

    fun deleteTask(id: Int) {
        runAsync({ repository.deleteByTaskId(id) }) {
            navigation.updateUi(TaskBoardScreen)
        }
    }

    fun addTime(time: Int, idTask: Int) {
        runAsync({ repository.addTimeForTask(time, idTask) },
            {
                navigation.updateUi(TaskDetailsScreen(idTask))
                init(idTask)
            })
    }
}

