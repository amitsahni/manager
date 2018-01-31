package com.fragment.di;

import android.app.Fragment;
import android.support.annotation.NonNull;

import com.fragment.FragParam;

/**
 * Created by clickapps on 1/11/17.
 */

public interface IFragmentProperties<T> {

    T enableAnimation(boolean enableAnimation);

    T animation(int enter, int exit, int popEnter, int popExit);
}
