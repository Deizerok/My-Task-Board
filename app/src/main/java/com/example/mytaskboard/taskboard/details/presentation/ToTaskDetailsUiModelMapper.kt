package com.example.mytaskboard.taskboard.details.presentation

import com.example.mytaskboard.taskboard.board.domain.TaskItem
import javax.inject.Inject

class ToTaskDetailsUiModelMapper @Inject constructor() :
    TaskItem.Mapper<TaskDetailsUiModel> {
    override fun map(
        id: Int,
        title: String,
        description: String,
        time: Int,
        picture: ByteArray
    ): TaskDetailsUiModel = TaskDetailsUiModel(id, title, time, description, picture)

}