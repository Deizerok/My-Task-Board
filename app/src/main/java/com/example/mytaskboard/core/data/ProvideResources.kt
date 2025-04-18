package com.example.mytaskboard.core.data

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface ProvideResources {

    fun stringById(@StringRes id: Int): String

    @Singleton
    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : ProvideResources {

        override fun stringById(id: Int) = context.getString(id)
    }
}