package com.activityutil.activity.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;

import com.activityutil.activity.Constants;

import java.util.Locale;

/**
 * Created by clickapps on 4/9/17.
 */

public class LanguageContextWrapper extends ContextWrapper {

    public LanguageContextWrapper(Context base) {
        super(base);
    }

    static ContextWrapper wrap(Context context, String language) {
        return wrap(context, language, false);
    }

    public static ContextWrapper wrap(Context context, String language, boolean isBroadCast) {
        if (!language.equals("")) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Configuration configuration = context.getResources().getConfiguration();
                configuration.setLocale(locale);
                configuration.setLayoutDirection(locale);
                context.createConfigurationContext(configuration);
            } else {
                Resources resources = context.getResources();
                Configuration configuration = context.getResources().getConfiguration();
                configuration.locale = locale;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLayoutDirection(locale);
                }
                resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            }
            if (isBroadCast)
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constants.getActionBroadcastLanguageChanged()));
        }
        return new LanguageContextWrapper(context);
    }

    @SuppressWarnings("deprecation")
    public static Locale getSystemLocaleLegacy(Configuration config) {
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config) {
        return config.getLocales().get(0);
    }

    @SuppressWarnings("deprecation")
    public static void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }
}
