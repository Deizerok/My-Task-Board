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
                .putString(KEY_SELECTED_LANGUAGE, language.name)
                .putInt(KEY_SELECTED_ICON, language.iconResId)
                .apply()
        }

        override fun get(): Language {
            val languageName = sharedPreferences.getString(KEY_SELECTED_LANGUAGE, null)
            val iconResId = sharedPreferences.getInt(KEY_SELECTED_ICON, -1)

            return if (languageName != null && iconResId != -1) {
                Language(languageName, iconResId)
            } else Language(name = "US", iconResId = R.drawable.circle_us_flag)
        }

        companion object {
            private const val KEY_SELECTED_LANGUAGE = "selected_language"
            private const val KEY_SELECTED_ICON = "selected_icon"
        }
    }
}