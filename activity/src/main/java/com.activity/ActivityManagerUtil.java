package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;


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
        ActivityParam.ActivityType activityType = activityParam.activityType;
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
        Intent intent = new Intent(activityParam.context, activityParam.uri);
        if (activityParam.flag == 0) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.setFlags(activityParam.flag);
        }
        if (activityParam.bundle != null) {
            intent.putExtras(activityParam.bundle);
        }
        if (activityParam.activityOptionsCompat != null
                && Build.VERSION.SDK_INT >= 21) {
            activityParam.context.startActivity(intent, activityParam.activityOptionsCompat.toBundle());
        } else {
            activityParam.context.startActivity(intent);
        }
        if (activityParam.activityType == ActivityParam.ActivityType.START_FINISH) {
            finish(activityParam);
        }
    }

    private static void startResult(ActivityParam activityParam) {
        Intent intent = new Intent(activityParam.context, activityParam.uri);
        if (activityParam.flag != 0) {
            intent.setFlags(activityParam.flag);
        }
        if (activityParam.bundle != null) {
            intent.putExtras(activityParam.bundle);
        }
        if (activityParam.activityOptionsCompat != null
                && Build.VERSION.SDK_INT >= 21) {
            activityParam.context.startActivityForResult(intent, activityParam.requestCode, activityParam.activityOptionsCompat.toBundle());
        } else {
            activityParam.context.startActivityForResult(intent, activityParam.requestCode);
        }
        if (activityParam.activityType == ActivityParam.ActivityType.START_RESULT_FINISH) {
            finish(activityParam);
        }
    }

    private static void finish(ActivityParam activityParam) {
        activityParam.context.finish();
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
