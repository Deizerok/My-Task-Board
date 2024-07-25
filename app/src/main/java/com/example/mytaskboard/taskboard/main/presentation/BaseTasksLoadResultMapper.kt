package com.example.mytaskboard.taskboard.main.presentation

import com.example.mytaskboard.core.domain.LoadResult
import com.example.mytaskboard.taskboard.main.domain.TaskItem
import com.example.mytaskboard.taskboard.main.presentation.adapter.TaskUi
import javax.inject.Inject

class BaseTasksLoadResultMapper @Inject constructor(
    private val communication: TasksLiveDataWrapper,
    private val taskItemToProductUiMapper: TaskItem.Mapper<TaskUi>
) : LoadResult.Mapper<TaskItem> {

    override fun mapSuccess(items: List<TaskItem>) {
        communication.updateUi(
            if (items.isEmpty()) {
                TasksUiState.NoTasks
            } else {
                TasksUiState.Success(
                    items.map {
                        it.map(taskItemToProductUiMapper)
                    })
            }
        )
    }

    override fun mapError(message: String) = Unit
}