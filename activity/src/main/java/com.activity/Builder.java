package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;

import com.fragment.di.IActivityProperties;

/**
 * Created by clickapps on 1/11/17.
 */

public class Builder implements IActivityProperties {

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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public Builder activityCompactOption(ActivityOptionsCompat activityOptionsCompat) {
        activityParam.activityOptionsCompat = activityOptionsCompat;
        return this;
    }

    /**
     * Build.
     */
    @Override
    public void build() {
        if (activityParam.context == null) {
            Log.e(getClass().getSimpleName(), "Context is null");
            return;
        }
        ActivityManagerUtil.performTask(activityParam);
    }
}
