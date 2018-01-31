package com.base.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.base.Utils;
import com.base.interfaces.ConnectivityListener;

/**
 * Created by amit on 20/2/17.
 */

public class InternetBroadCastReceiver extends BroadcastReceiver {
    public ConnectivityListener connectivityListener;

    public void addCallback(ConnectivityListener listener) {
        this.connectivityListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = Utils.isInternetConnected(context);
        if (connectivityListener != null) {
            connectivityListener.onConnectivityChange(isConnected);
        }
    }
}
