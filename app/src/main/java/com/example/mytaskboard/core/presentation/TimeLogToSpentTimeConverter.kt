package com.example.mytaskboard.core.presentation

import com.example.mytaskboard.taskboard.todo.domain.TimeLogEntry
import javax.inject.Inject

interface TimeLogToSpentTimeConverter {

    fun convert(timeLog: List<TimeLogEntry>): String

    class Base @Inject constructor() : TimeLogToSpentTimeConverter {

        override fun convert(timeLog: List<TimeLogEntry>): String {
            var seconds = 0
            var minutes = 0
            var hours = 0
            timeLog.forEach {
                seconds += it.seconds
                minutes += it.minutes
                hours += it.hours
            }

            minutes += seconds / 60
            seconds %= 60
            hours += minutes / 60
            minutes %= 60

            return "${hours}h ${minutes}m ${seconds}s"
        }
    }
}