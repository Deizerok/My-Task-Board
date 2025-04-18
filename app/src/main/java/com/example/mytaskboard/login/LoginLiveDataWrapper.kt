package com.example.mytaskboard.login

import com.example.mytaskboard.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface LoginLiveDataWrapper: LiveDataWrapper<LoginUiState> {

    class Base @Inject constructor(): LoginLiveDataWrapper, LiveDataWrapper.Single<LoginUiState>()
}