package com.example.mytaskboard.taskboard.details.presentation

import androidx.lifecycle.LiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.MessageLiveDataWrapper
import com.example.mytaskboard.core.presentation.RunAsync
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.taskboard.details.domain.TaskDetailsRepository
import com.example.mytaskboard.taskboard.details.presentation.stopwatch.Stopwatch
import com.example.mytaskboard.taskboard.details.presentation.stopwatch.StopwatchUiState
import com.example.mytaskboard.taskboard.details.presentation.stopwatch.StopwatchWorker
import com.example.mytaskboard.taskboard.details.presentation.stopwatch.StopwatchWorker.Companion.KEY_TASK_TITLE
import com.example.mytaskboard.taskboard.todo.domain.TaskItem
import com.example.mytaskboard.taskboard.todo.presentation.TaskBoardScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val stopwatch: Stopwatch,
    private val stopwatchLiveDataWrapper: StopwatchLiveDataWrapper,
    private val taskLiveDataWrapper: TaskDetailsLiveDataWrapper,
    private val messageLiveDataWrapper: MessageLiveDataWrapper,
    private val navigation: Navigation.Navigate,
    private val repository: TaskDetailsRepository,
    private val mapper: TaskItem.Mapper<TaskDetailsUiModel>,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun init(id: Int) = runAsync({
        coroutineScope.launch(Dispatchers.IO) {
            stopwatch.timeFlow
                .filter { it != 0 && id == stopwatch.runningTaskId() }
                .collect { seconds ->
                    val hours = seconds / 3600
                    val minutes = (seconds % 3600) / 60
                    val secs = seconds % 60
                    stopwatchLiveDataWrapper.postUpdateUi(
                        StopwatchUiState.Time(
                            value = String.format("%02d:%02d:%02d", hours, minutes, secs)
                        )
                    )
                }
        }

        repository.task(id)
    }, { taskItem ->
        taskLiveDataWrapper.updateUi(taskItem.map(mapper))
    })

    fun stopwatchActionClick(id: Int) {
        if (stopwatch.isRunning() && id == stopwatch.runningTaskId()) {
            val seconds = stopwatch.reset()
            stopwatchLiveDataWrapper.updateUi(StopwatchUiState.Initial)

            workManager.cancelUniqueWork(WORK_NAME)

            if (seconds <= 10) {
                messageLiveDataWrapper.updateUi("Session lasted less than 10 seconds")
            } else {
                runAsync({
                    repository.addSessionTime(
                        taskId = id,
                        date = Date().time,
                        hours = seconds / 3600,
                        minutes = (seconds % 3600) / 60,
                        seconds = seconds % 60
                    )
                    repository.task(id)
                }) { taskItem ->
                    taskLiveDataWrapper.updateUi(taskItem.map(mapper))
                }
            }
        } else if (!stopwatch.isRunning()) {
            stopwatch.start(taskId = id)
            val stopwatchWorkRequest = OneTimeWorkRequestBuilder<StopwatchWorker>()
                .setInputData(
                    Data.Builder()
                        .putString(
                            KEY_TASK_TITLE,
                            taskLiveDataWrapper.liveData().value!!.getTitle()
                        )
                        .build()
                )
                .build()

            workManager.enqueueUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.KEEP,
                stopwatchWorkRequest
            )
        } else {
            messageLiveDataWrapper.updateUi("Stop session in another task before starting a new one")
        }
    }

    fun finishTask(id: Int) {
        if (stopwatch.isRunning() && stopwatch.runningTaskId() == id) {
            messageLiveDataWrapper.updateUi("Before finishing the task stop a session")
        } else {
            runAsync({
                repository.finishTask(id)
            }) {
                navigation.updateUi(TaskBoardScreen)
            }
        }
    }

    fun deleteTask(id: Int) {
        if (stopwatch.isRunning() && stopwatch.runningTaskId() == id) {
            messageLiveDataWrapper.updateUi("Before deleting the task stop a session")
        } else {
            runAsync({
                repository.deleteByTaskId(id)
            }) {
                navigation.updateUi(TaskBoardScreen)
            }
        }
    }

    fun back() = navigation.updateUi(TaskBoardScreen)

    fun taskLiveData(): LiveData<TaskDetailsUiModel> = taskLiveDataWrapper.liveData()
    fun stopwatchLiveData(): LiveData<StopwatchUiState> = stopwatchLiveDataWrapper.liveData()
    fun messageLiveData(): LiveData<String> = messageLiveDataWrapper.liveData()

    companion object {
        const val WORK_NAME = "STOPWATCH_WORKER"
    }
}