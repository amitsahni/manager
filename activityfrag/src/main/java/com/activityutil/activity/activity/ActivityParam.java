package com.activityutil.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;


/**
 * The type Activity param.
 */
public class ActivityParam {

    /**
     * The Context.
     */
    Activity context;
    /**
     * The Uri.
     */
    Class<?> uri;
    /**
     * The Request code.
     */
    int requestCode = 0;
    /**
     * The Flag.
     */
    int flag = 0;
    /**
     * The Activity type.
     */
    ActivityType activityType = ActivityType.START;
    /**
     * The Bundle.
     */
    Bundle bundle;
    /**
     * The Activity options compat.
     */
    ActivityOptionsCompat activityOptionsCompat;


    /**
     * The enum Activity type.
     */
    public enum ActivityType {
        /**
         * Start activity type.
         */
        START, /**
         * Start result activity type.
         */
        START_RESULT, /**
         * Finish activity type.
         */
        FINISH, /**
         * Start finish activity type.
         */
        START_FINISH, /**
         * Start result finish activity type.
         */
        START_RESULT_FINISH
    }
}
