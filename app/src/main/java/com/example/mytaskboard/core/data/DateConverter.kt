package com.example.mytaskboard.core.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

interface DateConverter {

    fun getUTCDate(): Long

    fun convertToLocal(date: Long): String

    class Base @Inject constructor() : DateConverter {

        private val localFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        override fun getUTCDate(): Long = Date().time

        override fun convertToLocal(date: Long): String {
            localFormatter.timeZone = TimeZone.getDefault()
            return localFormatter.format(Date(date))
        }
    }
}