package com.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;


/**
 * The type Activity param.
 */
public class ActivityParam {

    Context context;
    Class<?> uri;
    int requestCode = 0, flag = 0, enterAnim = 0, exitAnim = 0;
    boolean isAnimation = false;
    ActivityType activityType = ActivityType.START;
    Bundle bundle;
    ActivityOptionsCompat activityOptionsCompat;
    View[] sharedElements;

    public enum ActivityType {
        START,
        START_RESULT,
        FINISH,
        START_FINISH,
        START_RESULT_FINISH
    }
}
