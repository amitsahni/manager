package com.common.broadcast

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.common.Constants

class FragmentOnActivityResultLiveData(val context: Context) : LiveData<Intent>() {

    private val filter = IntentFilter(Constants.getActionBroadcastOnResult())
    override fun onActive() {
        super.onActive()
        LocalBroadcastManager.getInstance(context).registerReceiver(onResultBroadCastReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        LocalBroadcastManager.getInstance(context).unregisterReceiver(onResultBroadCastReceiver)
    }

    private val onResultBroadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            postValue(intent)
        }
    }


}