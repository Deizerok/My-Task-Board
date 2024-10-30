package com.example.mytaskboard.taskboard.details.presentation

import android.util.Log
import com.example.mytaskboard.core.presentation.LiveDataWrapper
import com.example.mytaskboard.taskboard.details.presentation.stopwatch.StopwatchUiState
import javax.inject.Inject

interface StopwatchLiveDataWrapper : LiveDataWrapper<StopwatchUiState> {

    fun postUpdateUi(value: StopwatchUiState)

    class Base @Inject constructor() : StopwatchLiveDataWrapper,
        LiveDataWrapper.Single<StopwatchUiState>() {

        init {
            Log.d("k0dm", "StopwatchLiveDataWrapper init ${hashCode()}")
        }

        override fun postUpdateUi(value: StopwatchUiState) {
            liveData.postValue(value)
        }
    }
}