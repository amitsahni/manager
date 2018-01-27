package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;


/**
 * The type Activity param.
 */
public class ActivityParam {

    Activity context;
    Class<?> uri;
    int requestCode = 0;
    int flag = 0;
    ActivityType activityType = ActivityType.START;
    Bundle bundle;
    ActivityOptionsCompat activityOptionsCompat;

    public enum ActivityType {
        START,
        START_RESULT,
        FINISH,
        START_FINISH,
        START_RESULT_FINISH
    }
}