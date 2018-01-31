package com.activity.di;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;

import com.activity.ActivityParam;


/**
 * Created by clickapps on 1/11/17.
 */

public interface IActivityProperties {

    IActivityProperties bundle(Bundle bundle);

    IActivityProperties klass(Class<?> uri);

    IActivityProperties activityType(@NonNull ActivityParam.ActivityType activityType);

    IActivityProperties requestCode(int requestCode);

    IActivityProperties flag(int flag);

    IActivityProperties activityCompactOption(ActivityOptionsCompat activityOptionsCompat);

    void build();
}
