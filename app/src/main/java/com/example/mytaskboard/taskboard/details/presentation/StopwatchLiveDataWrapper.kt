package com.example.mytaskboard.taskboard.details.presentation

import com.example.mytaskboard.core.presentation.LiveDataWrapper
import com.example.mytaskboard.taskboard.details.presentation.stopwatch.StopwatchUiState
import javax.inject.Inject

interface StopwatchLiveDataWrapper : LiveDataWrapper<StopwatchUiState> {

    fun postUpdateUi(value: StopwatchUiState)

    class Base @Inject constructor() : StopwatchLiveDataWrapper,
        LiveDataWrapper.Single<StopwatchUiState>() {

        override fun postUpdateUi(value: StopwatchUiState) {
            liveData.postValue(value)
        }
    }
}