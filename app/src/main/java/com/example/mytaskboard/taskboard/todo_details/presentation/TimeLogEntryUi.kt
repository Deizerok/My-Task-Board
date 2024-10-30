package com.example.mytaskboard.taskboard.todo_details.presentation

interface TimeLogEntryUi {

    fun time(): String

    data class Base(private val time: String) : TimeLogEntryUi {
        override fun time() = time
    }

    object NoEntries : TimeLogEntryUi {
        override fun time() = "No entries"
    }
}