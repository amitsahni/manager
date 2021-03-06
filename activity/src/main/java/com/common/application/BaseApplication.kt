package com.common.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.multidex.MultiDex
import androidx.appcompat.app.AppCompatActivity
import com.activity.BaseAppCompatActivity
import com.common.Constants
import com.common.broadcast.ConnectionLiveData


/**
 * The type Base application.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
open class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {

    var activity: AppCompatActivity? = null
        private set

    override fun onCreate() {
        super.onCreate()
        packageName = packageName
        val connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observeForever {
            it?.let {
                if (activity is BaseAppCompatActivity) {
                    (activity as BaseAppCompatActivity).onConnectivityChange(it)
                }
            }
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // Nothing used
        if (activity is BaseAppCompatActivity) {
            this.activity = activity
        }
    }

    override fun onActivityStarted(activity: Activity) {
        // Nothing used
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity is BaseAppCompatActivity) {
            this.activity = activity
        }
    }

    override fun onActivityPaused(activity: Activity) {
        // Nothing used
    }

    override fun onActivityStopped(activity: Activity) {
        // Nothing used
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // Nothing used
    }

    override fun onActivityDestroyed(activity: Activity) {
        // Nothing used
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base);
        MultiDex.install(this)
        registerActivityLifecycleCallbacks(this)
    }

    fun setPackageName(packageName: String) {
        Constants.setPackageName(packageName)
    }
}
