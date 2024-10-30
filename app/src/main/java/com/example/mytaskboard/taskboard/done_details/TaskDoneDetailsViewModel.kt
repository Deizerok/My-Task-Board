package com.example.mytaskboard.taskboard.done_details

import androidx.lifecycle.LiveData
import com.example.fakestore.core.presentation.ProvideLiveData
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.taskboard.details.domain.TaskDetailsRepository
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.presentation.TaskBoardScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDoneDetailsViewModel @Inject constructor(
    private val taskLiveDataWrapper: TaskDoneDetailsLiveDataWrapper,
    private val navigation: Navigation.Navigate,
    private val repository: TaskDetailsRepository,
    private val mapper: TaskItem.Mapper<TaskDoneDetailsUiModel>,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<TaskDoneDetailsUiModel> {

    fun init(id: Int) = runAsync({
        repository.task(id)
    }, { taskItem ->
        taskLiveDataWrapper.updateUi(taskItem.map(mapper))
    })

    fun openBottomSheetRestore(id: Int) {
        navigation.updateUi(BottomSheetRestoreTaskScreen(id))
    }

    fun openBottomSheetDelete(id: Int) {
        navigation.updateUi(BottomSheetDeleteTaskScreen(id))
    }

    fun restoreTask(id: Int) = runAsync({
        repository.restoreTask(id)
    }) {
        navigation.updateUi(TaskBoardScreen)
    }

    fun deleteTask(id: Int) = runAsync({
        repository.deleteByTaskId(id)
    }) {
        navigation.updateUi(TaskBoardScreen)
    }

    fun back() = navigation.updateUi(TaskBoardScreen)

    override fun liveData(): LiveData<TaskDoneDetailsUiModel> = taskLiveDataWrapper.liveData()
}

