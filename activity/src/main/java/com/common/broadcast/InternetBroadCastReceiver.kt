package com.common.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.common.Utils
import com.common.interfaces.ConnectivityListener


/**
 * Created by amit on 20/2/17.
 */

class InternetBroadCastReceiver : BroadcastReceiver() {
    private var connectivityListener: ConnectivityListener? = null

    fun addCallback(listener: ConnectivityListener) {
        this.connectivityListener = listener
    }

    override fun onReceive(context: Context, intent: Intent) {
        val isConnected = Utils.isInternetConnected(context)
        connectivityListener?.onConnectivityChange(isConnected)
    }
}
