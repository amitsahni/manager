package com.activityutil.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;


/**
 * The type Activity param.
 */
public class ActivityParam {

    /**
     * The Context.
     */
    private Activity context;
    /**
     * The Uri.
     */
    private Class<?> uri;
    /**
     * The Request code.
     */
    private int requestCode = 0;
    /**
     * The Flag.
     */
    private int flag = 0;
    /**
     * The Activity type.
     */
    private ActivityType activityType = ActivityType.START;
    /**
     * The Bundle.
     */
    private Bundle bundle;
    /**
     * The Activity options compat.
     */
    private ActivityOptionsCompat activityOptionsCompat;


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

    public Activity getContext() {
        return context;
    }

    public Class<?> getUri() {
        return uri;
    }


    public int getRequestCode() {
        return requestCode;
    }

    public int getFlag() {
        return flag;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ActivityOptionsCompat getActivityOptionsCompat() {
        return activityOptionsCompat;
    }


    /**
     * The type Builder.
     */
    public static class Builder {

        private ActivityParam activityParam;

        /**
         * Instantiates a new Builder.
         *
         * @param context the context
         */
        public Builder(@NonNull Activity context) {
            activityParam = new ActivityParam();
            activityParam.context = context;
        }

        /**
         * Instantiates a new Builder.
         *
         * @param context      the context
         * @param activityType the activity type
         */
        public Builder(@NonNull Activity context, ActivityParam.ActivityType activityType) {
            activityParam = new ActivityParam();
            activityParam.context = context;
            activityParam.activityType = activityType;
        }

        /**
         * Bundle builder.
         *
         * @param bundle the bundle
         * @return the builder
         */
        public Builder bundle(Bundle bundle) {
            activityParam.bundle = bundle;
            return this;
        }

        /**
         * Klass builder.
         *
         * @param uri the uri
         * @return the builder
         */
        public Builder klass(Class<?> uri) {
            activityParam.uri = uri;
            return this;
        }

        /**
         * Activity type builder.
         *
         * @param activityType the activity type
         * @return the builder
         */
        public Builder activityType(@NonNull ActivityParam.ActivityType activityType) {
            activityParam.activityType = activityType;
            return this;
        }

        /**
         * Request code builder.
         *
         * @param requestCode the request code
         * @return the builder
         */
        public Builder requestCode(int requestCode) {
            activityParam.requestCode = requestCode;
            return this;
        }


        /**
         * Flag builder.
         *
         * @param flag the flag
         * @return the builder
         */
        public Builder flag(int flag) {
            activityParam.flag = flag;
            return this;
        }

        /**
         * Activity compact option builder.
         *
         * @param activityOptionsCompat the activity options compat
         * @return the builder
         */
        public Builder activityCompactOption(ActivityOptionsCompat activityOptionsCompat) {
            activityParam.activityOptionsCompat = activityOptionsCompat;
            return this;
        }

        /**
         * Build.
         */
        public void build() {
            if (activityParam.context == null) {
                Log.e(getClass().getSimpleName(), "Context is null");
                return;
            }
            ActivityManagerUtil.performTask(activityParam);
        }
    }
}
