package com.common;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Locale;

/**
 * Created by clickapps on 4/9/17.
 */

public class LanguageContextWrapper extends ContextWrapper {

    public LanguageContextWrapper(Context base) {
        super(base);
    }

    public static ContextWrapper wrap(Context context, String language) {
        return wrap(context, language, false);
    }

    public static ContextWrapper wrap(Context context, String language, boolean isBroadCast) {
        if (!language.equals("")) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale);
                LocaleList localeList = new LocaleList(locale);
                LocaleList.setDefault(localeList);
                configuration.setLocales(localeList);
                configuration.setLayoutDirection(locale);
                context.createConfigurationContext(configuration);
            } else {
                configuration.locale = locale;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLayoutDirection(locale);
                }
            }
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            if (isBroadCast)
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constants.getActionBroadcastLanguageChanged()));
        }
        return new LanguageContextWrapper(context);
    }

}
