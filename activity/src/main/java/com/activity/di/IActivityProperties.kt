package com.activity.di

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.View


/**
 * Created by clickapps on 1/11/17.
 */

interface IActivityProperties<T> {

    fun bundle(bundle: Bundle): T

    fun flag(flag: Int): T

    fun animation(enterAnim: Int, exitAnim: Int): T

    fun activityCompactOption(activityOptionsCompat: ActivityOptionsCompat): T

    fun sharedElements(vararg sharedElements: View): T
}
