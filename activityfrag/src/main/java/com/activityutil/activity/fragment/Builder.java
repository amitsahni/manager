package com.activityutil.activity.fragment;

import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.activityutil.activity.di.IFragmentProperties;

/**
 * Created by clickapps on 1/11/17.
 */

public class Builder implements IFragmentProperties {
    private FragParam mFragParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context   the context
     * @param replaceId the replace id
     */
    public Builder(@NonNull FragmentActivity context, @IdRes int replaceId) {
        mFragParam = new FragParam();
        mFragParam.context = context;
        mFragParam.replaceId = replaceId;
    }

    /**
     * Fragment builder.
     *
     * @param fragment the fragment
     * @return the builder
     */
    @Override
    public Builder fragment(@NonNull Fragment fragment) {
        mFragParam.fragment = fragment;
        return this;
    }

    /**
     * Tag builder.
     *
     * @param tag the tag
     * @return the builder
     */
    @Override
    public Builder tag(@NonNull String tag) {
        mFragParam.tag = tag;
        return this;
    }

    /**
     * Enable animation builder.
     *
     * @param enableAnimation the enable animation
     * @return the builder
     */
    @Override
    public Builder enableAnimation(boolean enableAnimation) {
        mFragParam.enableAnimation = enableAnimation;
        return this;
    }

    /**
     * Type builder.
     *
     * @param fragType the frag type
     * @return the builder
     */
    @Override
    public Builder type(@NonNull FragParam.FragType fragType) {
        mFragParam.fragType = fragType;
        return this;
    }

    /**
     * Back stack builder.
     *
     * @param isBackStack the is back stack
     * @return the builder
     */
    @Override
    public Builder backStack(boolean isBackStack) {
        mFragParam.isBackStack = isBackStack;
        return this;
    }

    /**
     * Animation builder.
     *
     * @param enter    the enter
     * @param exit     the exit
     * @param popEnter the pop enter
     * @param popExit  the pop exit
     * @return the builder
     */
    @Override
    public Builder animation(int enter, int exit, int popEnter, int popExit) {
        mFragParam.enter = enter;
        mFragParam.exit = exit;
        mFragParam.popEnter = popEnter;
        mFragParam.popExit = popExit;
        return this;
    }


    /**
     * Build.
     */
    @Override
    public void build() {
        if (mFragParam.context == null) {
            Log.e(getClass().getSimpleName(), "Context is null");
            return;
        }
        FragmentManagerUtil.performTask(mFragParam);
    }
}
