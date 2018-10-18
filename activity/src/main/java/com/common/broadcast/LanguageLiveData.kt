package com.common.broadcast

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.NetworkInfo
import android.support.v4.content.LocalBroadcastManager
import com.common.Constants

class LanguageLiveData(val context: Context) : LiveData<Boolean>() {
    private val filter = IntentFilter(Constants.getActionBroadcastLanguageChanged())

    override fun onActive() {
        super.onActive()
        LocalBroadcastManager.getInstance(context).registerReceiver(languageBroadCastReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
//        LocalBroadcastManager.getInstance(context).unregisterReceiver(languageBroadCastReceiver)

    }


    private val languageBroadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            postValue(true)
        }
    }
}