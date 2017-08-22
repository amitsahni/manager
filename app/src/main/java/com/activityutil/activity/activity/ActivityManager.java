package com.activityutil.activity.activity;

import android.app.Activity;
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
     * @see #with(Activity, ActivityParam.ActivityType) #with(android.app.Activity, ActivityParam.ActivityType)#with(android.app.Activity, ActivityParam.ActivityType)
     */
    public static ActivityParam.Builder with(@NonNull Activity context) {
        return new ActivityParam.Builder(context);
    }

    /**
     * Use Activity manager
     *
     * @param context      The activity to use.
     * @param activityType the activity type
     * @return A builder that can be used to pass values
     * @see #with(Activity) #with(android.app.Activity)#with(android.app.Activity)
     */
    public static ActivityParam.Builder with(@NonNull Activity context, ActivityParam.ActivityType activityType) {
        return new ActivityParam.Builder(context, activityType);
    }


}
