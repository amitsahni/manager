package com.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.activityutil.BuildConfig;

/**
 * Created by clickapps on 1/8/17.
 */

public class Utils {
    private static final String TAG = "Utils";

    /**
     * Functionality to check the Internet availability
     *
     * @param context Activity context
     * @return true if Internet is connected
     */
    public static boolean isInternetConnected(Context context) {
        boolean isWifiConnected = false;
        boolean isMobileInternetConnected = false;
        if (context != null) {
            try {

                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) { // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        isWifiConnected = true;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // connected to the mobile provider's data plan
                        isMobileInternetConnected = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "isInternetConnected() -> Context is null");
            }
        }
        return isWifiConnected || isMobileInternetConnected;
    }
}
