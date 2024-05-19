package com.example.fakestore.core

interface UiUpdate<T : Any> {

    fun updateUi(value: T)
}