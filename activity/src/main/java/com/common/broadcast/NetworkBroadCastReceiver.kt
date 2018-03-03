package com.common.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager

import com.common.Constants


/**
 * Created by amit on 15/2/17.
 */

class NetworkBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(Intent(Constants.getActionBroadcastNetworkChanged()))
    }
}
