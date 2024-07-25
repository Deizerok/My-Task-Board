package com.example.mytaskboard.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface RunAsync {

    fun <T : Any> start(
        backgroundBlock: suspend () -> T,
        uiBlock: (T) -> Unit,
        coroutineScope: CoroutineScope
    )

    @Singleton
    class Base @Inject constructor() : RunAsync {
        override fun <T : Any> start(
            backgroundBlock: suspend () -> T,
            uiBlock: (T) -> Unit,
            coroutineScope: CoroutineScope
        ) {
            coroutineScope.launch(Dispatchers.IO) {
                val result = backgroundBlock.invoke()
                withContext(Dispatchers.Main) {
                    uiBlock.invoke(result)
                }
            }
        }
    }
}