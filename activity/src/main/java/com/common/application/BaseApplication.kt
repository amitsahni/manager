package com.common.application

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.multidex.MultiDex
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity

import com.common.Constants
import com.common.broadcast.InternetBroadCastReceiver
import com.common.broadcast.NetworkBroadCastReceiver
import com.common.interfaces.OnBackHandler


/**
 * The type Base application.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
open class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {
    var internetBroadCastReceiver: InternetBroadCastReceiver? = null
        private set
    private var filter: IntentFilter? = null
    /**
     * Gets back handler.
     *
     * @return the back handler
     */
    /**
     * Sets back handler.
     *
     * @param mBackHandler the back handler
     */
    var backHandler: OnBackHandler? = null
    var fragment: Fragment? = null
        private set

    override fun onCreate() {
        super.onCreate()
        packageName = packageName
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(NetworkBroadCastReceiver(),
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        internetBroadCastReceiver = InternetBroadCastReceiver()
    }

    /**
     * Sets fragment when called another activity or application eg camera or gallery
     * After completed operation set this to null.
     *
     * @param fragment the fragment
     */
    fun setOnActivityResultFragment(fragment: Fragment?) {
        this.fragment = fragment
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // Nothing used
        Constants.setTopActivity(activity as AppCompatActivity)
    }

    override fun onActivityStarted(activity: Activity) {
        // Nothing used
    }

    override fun onActivityResumed(activity: Activity) {
        Constants.setTopActivity(activity as AppCompatActivity)
        LocalBroadcastManager.getInstance(activity).registerReceiver(internetBroadCastReceiver!!, getFilter())
    }

    override fun onActivityPaused(activity: Activity) {
        // Nothing used
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(internetBroadCastReceiver!!)
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
        super.attachBaseContext(base)
        MultiDex.install(this)
        registerActivityLifecycleCallbacks(this)
    }

    fun setPackageName(packageName: String) {
        Constants.setPackageName(packageName)
    }

    fun getFilter(): IntentFilter {
        if (filter == null) {
            filter = IntentFilter()
            filter?.addAction(Constants.getActionBroadcastNetworkChanged())
        }
        return filter as IntentFilter
    }
}
