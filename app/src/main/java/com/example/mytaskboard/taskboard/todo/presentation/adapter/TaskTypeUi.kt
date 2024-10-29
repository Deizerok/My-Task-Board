package com.example.mytaskboard.taskboard.todo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mytaskboard.databinding.ViewholderNoTaskBinding
import com.example.mytaskboard.databinding.ViewholderTaskBinding

interface TaskTypeUi {

    fun createViewHolder(parent: ViewGroup): TaskViewHolder

    object Base : TaskTypeUi {

        override fun createViewHolder(parent: ViewGroup) = TaskViewHolder.Base(
            ViewholderTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object Progress : TaskTypeUi {
        override fun createViewHolder(parent: ViewGroup): TaskViewHolder {
            TODO("Not yet implemented")
        }

    }

    object Empty : TaskTypeUi {
        override fun createViewHolder(parent: ViewGroup): TaskViewHolder =
            TaskViewHolder.Empty(
                ViewholderNoTaskBinding.inflate(
                    LayoutInflater.from((parent.context)), parent, false
                )
            )
    }

}
