package com.activityutil.activity.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.support.v4.content.LocalBroadcastManager;

import com.activityutil.activity.activity.ActivityManagerUtil;
import com.activityutil.activity.Constants;
import com.activityutil.activity.broadcast.InternetBroadCastReceiver;
import com.activityutil.activity.broadcast.NetworkBroadCastReceiver;


/**
 * The type Base application.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private InternetBroadCastReceiver internetBroadCastReceiver;
    private IntentFilter filter = null;

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
        ActivityManagerUtil.setTopActivity(activity);
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
            filter = new IntentFilter(Constants.getActionBroadcastNetworkChanged());
        }
        return filter;
    }
}
