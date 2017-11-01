package com.activityutil.activity.di;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;

import com.activityutil.activity.activity.ActivityParam;
import com.activityutil.activity.fragment.FragParam;

/**
 * Created by clickapps on 1/11/17.
 */

public interface IFragmentProperties {

    IFragmentProperties fragment(@NonNull Fragment fragment);

    IFragmentProperties tag(@NonNull String tag) ;

    IFragmentProperties  enableAnimation(boolean enableAnimation);

    IFragmentProperties type(@NonNull FragParam.FragType fragType);

    IFragmentProperties backStack(boolean isBackStack);

    IFragmentProperties animation(int enter, int exit, int popEnter, int popExit);
    void build();
}
