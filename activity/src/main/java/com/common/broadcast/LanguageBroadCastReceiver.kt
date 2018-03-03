package com.common.broadcast

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


/**
 * Created by clickapps on 24/2/16.
 *
 * Language change Broadcast is included in this [LanguageBroadCastReceiver].
 * Whenever language changes this broadcast hits  *
 */
class LanguageBroadCastReceiver
/**
 * Instantiates a new Language broad cast receiver.
 *
 * @param activity the activity
 */
(private val mActivity: Activity) : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        mActivity.recreate()
    }

}
