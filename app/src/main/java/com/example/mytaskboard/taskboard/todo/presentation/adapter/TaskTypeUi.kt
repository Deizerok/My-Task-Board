package com.example.mytaskboard.taskboard.todo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mytaskboard.databinding.ViewholderNoTaskBinding
import com.example.mytaskboard.databinding.ViewholderTaskBinding

interface TaskTypeUi {

    fun createViewHolder(parent: ViewGroup): TodoTaskViewHolder

    object Base : TaskTypeUi {

        override fun createViewHolder(parent: ViewGroup) = TodoTaskViewHolder.Base(
            ViewholderTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object Empty : TaskTypeUi {

        override fun createViewHolder(parent: ViewGroup): TodoTaskViewHolder =
            TodoTaskViewHolder.Empty(
                ViewholderNoTaskBinding.inflate(
                    LayoutInflater.from((parent.context)), parent, false
                )
            )
    }
}
