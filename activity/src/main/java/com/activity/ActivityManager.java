package com.activity;

import android.content.Context;
import android.support.annotation.NonNull;


/**
 * The type Activity manager.
 */
public class ActivityManager {
    private static volatile ActivityManager sActivityManager;

    private ActivityManager() {

    }

    /**
     * Get the singleton.
     *
     * @return the singleton
     */
    public static ActivityManager get() {
        if (sActivityManager == null) {
            synchronized (ActivityManager.class) {
                if (sActivityManager == null) {
                    sActivityManager = new ActivityManager();
                }
            }
        }
        return sActivityManager;
    }

    /**
     * Use Activity manager
     *
     * @param context The activity to use.
     * @return A builder that can be used to pass values
     */
    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }


}
