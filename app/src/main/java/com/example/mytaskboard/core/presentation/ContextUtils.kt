package com.example.mytaskboard.core.presentation

import android.content.Context
import android.content.ContextWrapper
import android.os.LocaleList
import java.util.Locale

class ContextUtils(base: Context) : ContextWrapper(base) {
    companion object {
        //https://lokalise.com/blog/android-app-localization/
        /*
        * Key points:
            1.Create a new configuration object using the current resource configuration.
            2.Create a LocaleList with the locale we want to switch to.
            3.Set the default locale for the app to the first locale in the list (in this case, the one we created).
            4.Update the configuration by setting the new locale list.
            5.Create a new context with the updated configuration, then return it.
        * */
        fun updateLocale(context: Context, localeToSwitchTo: Locale): ContextUtils {
            val resources = context.resources
            val configuration = resources.configuration // 1
            val localeList = LocaleList(localeToSwitchTo) // 2
            LocaleList.setDefault(localeList) // 3
            configuration.setLocales(localeList) // 4
            val updatedContext = context.createConfigurationContext(configuration) // 5
            return ContextUtils(updatedContext)
        }
    }
}