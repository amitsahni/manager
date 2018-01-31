package com.activity.di;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;


/**
 * Created by clickapps on 1/11/17.
 */

public interface IActivityProperties<T> {

    T bundle(Bundle bundle);

    T flag(int flag);

    T activityCompactOption(ActivityOptionsCompat activityOptionsCompat);

}
