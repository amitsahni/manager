package com.activityutil.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;


/**
 * The type Activity manager util.
 */
public class ActivityManagerUtil {
    private static Activity sCurrentActivity;

    private ActivityManagerUtil() {
        // Nothing
    }

    /**
     * Perform task.
     *
     * @param activityParam the activity param
     */
    protected static void performTask(ActivityParam activityParam) {
        ActivityParam.ActivityType activityType = activityParam.getActivityType();
        switch (activityType) {
            case START:
            case START_FINISH:
                start(activityParam);
                break;
            case START_RESULT:
            case START_RESULT_FINISH:
                startResult(activityParam);
                break;
            case FINISH:
                finish(activityParam);
                break;
            default:
                // nothing happened
                break;
        }
    }

    private static void start(ActivityParam activityParam) {
        Intent intent = new Intent(activityParam.getContext(), activityParam.getUri());
        if (activityParam.getFlag() == 0) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.setFlags(activityParam.getFlag());
        }
        if (activityParam.getBundle() != null) {
            intent.putExtras(activityParam.getBundle());
        }
        if (activityParam.getActivityOptionsCompat() != null
                && Build.VERSION.SDK_INT >= 21) {
            activityParam.getContext().startActivity(intent, activityParam.getActivityOptionsCompat().toBundle());
        } else {
            activityParam.getContext().startActivity(intent);
        }
        if (activityParam.getActivityType() == ActivityParam.ActivityType.START_FINISH) {
            finish(activityParam);
        }
    }

    private static void startResult(ActivityParam activityParam) {
        Intent intent = new Intent(activityParam.getContext(), activityParam.getUri());
        if (activityParam.getFlag() != 0) {
            intent.setFlags(activityParam.getFlag());
        }
        if (activityParam.getBundle() != null) {
            intent.putExtras(activityParam.getBundle());
        }
        if (activityParam.getActivityOptionsCompat() != null
                && Build.VERSION.SDK_INT >= 21) {
            activityParam.getContext().startActivityForResult(intent, activityParam.getRequestCode(), activityParam.getActivityOptionsCompat().toBundle());
        } else {
            activityParam.getContext().startActivityForResult(intent, activityParam.getRequestCode());
        }
        if (activityParam.getActivityType() == ActivityParam.ActivityType.START_RESULT_FINISH) {
            finish(activityParam);
        }
    }

    private static void finish(ActivityParam activityParam) {
        activityParam.getContext().finish();
    }


    /**
     * Gets top activity.
     *
     * @return the top activity
     */
    public static Activity getTopActivity() {
        return sCurrentActivity;
    }

    /**
     * Only set from BaseApplication
     *
     * @param currentActivity the current activity
     */
    public static void setTopActivity(@NonNull Activity currentActivity) {
        ActivityManagerUtil.sCurrentActivity = currentActivity;
    }
}
