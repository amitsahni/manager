package com.common.application;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import com.common.Constants;
import com.common.broadcast.InternetBroadCastReceiver;
import com.common.broadcast.NetworkBroadCastReceiver;
import com.common.interfaces.OnBackHandler;


/**
 * The type Base application.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private InternetBroadCastReceiver internetBroadCastReceiver;
    private IntentFilter filter = null;
    private OnBackHandler mBackHandler;
    private Fragment mFragment = null;

    @Override
    public void onCreate() {
        super.onCreate();
        setPackageName(getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(new NetworkBroadCastReceiver(),
                    new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        internetBroadCastReceiver = new InternetBroadCastReceiver();
    }

    /**
     * Sets back handler.
     *
     * @param mBackHandler the back handler
     */
    public void setBackHandler(OnBackHandler mBackHandler) {
        this.mBackHandler = mBackHandler;
    }

    /**
     * Sets mFragment when called another activity or application eg camera or gallery
     * After completed operation set this to null.
     *
     * @param fragment the mFragment
     */
    public void setOnActivityResultFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    /**
     * Gets back handler.
     *
     * @return the back handler
     */
    public OnBackHandler getBackHandler() {
        return mBackHandler;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // Nothing used
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Nothing used
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Constants.setTopActivity((AppCompatActivity) activity);
        LocalBroadcastManager.getInstance(activity).registerReceiver(internetBroadCastReceiver, getFilter());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Nothing used
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(internetBroadCastReceiver);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // Nothing used
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Nothing used
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Nothing used
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        registerActivityLifecycleCallbacks(this);
    }

    public void setPackageName(@NonNull String packageName) {
        Constants.setPackageName(packageName);
    }

    public InternetBroadCastReceiver getInternetBroadCastReceiver() {
        return internetBroadCastReceiver;
    }

    public IntentFilter getFilter() {
        if (filter == null) {
            filter = new IntentFilter();
            filter.addAction(Constants.getActionBroadcastNetworkChanged());
        }
        return filter;
    }
}
