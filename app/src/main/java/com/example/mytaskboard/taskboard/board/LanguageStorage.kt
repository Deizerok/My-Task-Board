package com.example.mytaskboard.taskboard.board

import android.content.Context
import android.content.SharedPreferences
import com.example.mytaskboard.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface LanguageStorage {

    fun save(language: Language)

    fun get(): Language

    class Base @Inject constructor(@ApplicationContext context: Context) : LanguageStorage {

        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("languageStorage", Context.MODE_PRIVATE)

        override fun save(language: Language) {
            sharedPreferences.edit()
                .putString(KEY_LANGUAGE, language.name)
                .putString(KEY_LOCAL, language.local)
                .putInt(KEY_ICON, language.iconResId)
                .apply()
        }

        override fun get(): Language {
            val languageName = sharedPreferences.getString(KEY_LANGUAGE, "UA")!!
            val local = sharedPreferences.getString(KEY_LOCAL, "uk")!!
            val iconResId = sharedPreferences.getInt(KEY_ICON, R.drawable.circle_ua_flag)

            return Language(name = languageName, local = local, iconResId = iconResId)
        }

        companion object {
            private const val KEY_LANGUAGE = "selected_language"
            private const val KEY_LOCAL = "selected_local"
            private const val KEY_ICON = "selected_icon"
        }
    }
}