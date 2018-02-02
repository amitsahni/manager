package com.activity.di;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;


/**
 * Created by clickapps on 1/11/17.
 */

public interface IActivityProperties<T> {

    T bundle(Bundle bundle);

    T flag(int flag);

    T animation(int enterAnim, int exitAnim);

    T activityCompactOption(ActivityOptionsCompat activityOptionsCompat);

    T sharedElements(View[] sharedElements);

}
