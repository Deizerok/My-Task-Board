package com.example.mytaskboard.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestore.core.UiUpdate
import com.example.fakestore.core.presentation.ProvideLiveData

interface LiveDataWrapper<T : Any> : UiUpdate<T>, ProvideLiveData<T> {

    abstract class Single<T : Any>(
        protected val liveData: MutableLiveData<T> = SingleLiveEvent()
    ) : LiveDataWrapper<T> {

        override fun updateUi(value: T) {
            liveData.value = value
        }

        override fun liveData(): LiveData<T> = liveData
    }
}