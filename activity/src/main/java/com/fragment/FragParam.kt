package com.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View


/**
 * The type Frag param.
 */
class FragParam {

    internal var context: Context? = null
    internal var replaceId: Int = 0
    internal var enter = 0
    internal var exit = 0
    internal var popEnter = 0
    internal var popExit = 0
    internal var fragment: Fragment? = null
    internal var tag: String? = null
    internal var enableAnimation = false
    internal var isBackStack = false
    internal var sharedElements: Array<View> = emptyArray()

}
