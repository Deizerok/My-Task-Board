package com.example.mytaskboard.taskboard.details.presentation.stopwatch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

class Stopwatch @Inject constructor() {

    private var isRunning: Boolean = false
    private var timer: Timer? = null
    private var seconds = 0

    private val _timeFlow = MutableStateFlow(0)
    val timeFlow: StateFlow<Int> = _timeFlow

    fun start() {
        isRunning = true
        timer = fixedRateTimer("stopwatch", false, 0, 1000) {
            seconds++
            _timeFlow.value = seconds
        }
    }

    fun reset(): Int {
        val totalSeconds = seconds
        isRunning = false
        timer?.cancel()
        seconds = 0
        return totalSeconds
    }

    fun isRunning() = isRunning
}