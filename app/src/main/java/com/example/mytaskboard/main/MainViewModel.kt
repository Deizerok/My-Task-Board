package com.example.mytaskboard.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable,
) : ViewModel() {

    fun navigationLiveData(): LiveData<Screen> = navigation.liveData()

    fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
         // todo   navigation.updateUi(CategoryScreen)
        }
    }



}