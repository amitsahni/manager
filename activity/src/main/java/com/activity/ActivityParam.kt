package com.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.View


/**
 * The type Activity param.
 */
class ActivityParam {

    internal var context: Context? = null
    internal var uri: Class<*>? = null
    internal var requestCode = 0
    internal var flag = 0
    internal var enterAnim = 0
    internal var exitAnim = 0
    internal var isAnimation = false
    internal var activityType = ActivityType.START
    internal var bundle = Bundle()
    internal var activityOptionsCompat: ActivityOptionsCompat? = null
    internal var sharedElements: Array<View> = arrayOf()

    enum class ActivityType {
        START,
        START_RESULT,
        FINISH,
        START_FINISH,
        START_RESULT_FINISH
    }
}
