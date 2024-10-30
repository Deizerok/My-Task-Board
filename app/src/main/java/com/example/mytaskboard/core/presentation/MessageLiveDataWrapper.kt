package com.example.mytaskboard.core.presentation

import javax.inject.Inject

interface MessageLiveDataWrapper : LiveDataWrapper<String> {

    class Base @Inject constructor() : MessageLiveDataWrapper, LiveDataWrapper.Single<String>()
}