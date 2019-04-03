package com.common

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.os.LocaleList
import android.preference.PreferenceManager
import android.support.v4.content.LocalBroadcastManager
import java.util.*

/**
 * Created by clickapps on 4/9/17.
 */

class LanguageContextWrapper private constructor(base: Context) : ContextWrapper(base) {
    companion object {

        @JvmStatic
        @JvmOverloads
        fun wrap(context: Context, language: String, isBroadCast: Boolean = false): ContextWrapper {
            var context = context
            val prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            prefEditor.putString("language", language)
            prefEditor.apply()
            val locale = Locale(language)
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                configuration.locales = localeList
                configuration.setLayoutDirection(locale)
                configuration.setLocale(locale)
                context = context.createConfigurationContext(configuration)
            } else {
                configuration.locale = locale
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLocale(locale)
                    configuration.setLayoutDirection(locale)
                    context = context.createConfigurationContext(configuration)
                }
            }
            resources.updateConfiguration(configuration, resources.displayMetrics)
            if (isBroadCast)
                LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(Constants.getActionBroadcastLanguageChanged()))
            return LanguageContextWrapper(context)
        }
    }

}
