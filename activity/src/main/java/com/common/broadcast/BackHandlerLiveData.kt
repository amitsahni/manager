package com.common.broadcast

import androidx.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.common.Constants

class BackHandlerLiveData(val context: Context) : LiveData<Boolean>() {
    private val filter = IntentFilter(Constants.getActionBroadcastBackHandler())
    override fun onActive() {
        super.onActive()
        LocalBroadcastManager.getInstance(context).registerReceiver(backHandlerBroadCastReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        LocalBroadcastManager.getInstance(context).unregisterReceiver(backHandlerBroadCastReceiver)
    }

    private val backHandlerBroadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            postValue(true)
        }
    }
}