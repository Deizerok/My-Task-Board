package com.example.mytaskboard.core.presentation

import android.content.Context
import android.util.Log

interface ManageResource {

    fun string(id: Int): String

    class Base(private val context: Context) : ManageResource {

        init {
            Log.d("k0dm", "${hashCode()} locale = ${context.resources.configuration.locales[0]}")
        }

        override fun string(id: Int) = context.getString(id)
    }
}