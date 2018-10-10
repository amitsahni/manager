package com.common;

import android.app.Activity;

/**
 * Created by clickapps on 1/8/17.
 */

public class Constants {

    private static String PACKAGE_NAME = "com.activityutil";

    private static final String ACTION_BROADCAST_NETWORK_CHANGED = ".android.base.util.CONNECTIVITY_CHANGE";

    private static final String ACTION_BROADCAST_LANGUAGE_CHANGED = ".android.base.util.LanguageChanged";

    private static final String ACTION_BROADCAST_BACK_HANDLE = ".android.base.util.BackHandle";

    private static final String ACTION_BROADCAST_ON_RESULT = ".android.base.util.OnResult";


    public static String getActionBroadcastNetworkChanged() {
        return PACKAGE_NAME + ACTION_BROADCAST_NETWORK_CHANGED;
    }

    public static String getActionBroadcastLanguageChanged() {
        return PACKAGE_NAME + ACTION_BROADCAST_LANGUAGE_CHANGED;
    }

    public static String getActionBroadcastBackHandler() {
        return PACKAGE_NAME + ACTION_BROADCAST_BACK_HANDLE;
    }

    public static String getActionBroadcastOnResult() {
        return PACKAGE_NAME + ACTION_BROADCAST_ON_RESULT;
    }

    public static void setPackageName(String packageName) {
        PACKAGE_NAME = packageName;
    }

    private static Activity sCurrentActivity;

    public static void setTopActivity(Activity sCurrentActivity) {
        Constants.sCurrentActivity = sCurrentActivity;
    }

    public static Activity getTopActivity() {
        return sCurrentActivity;
    }
}
