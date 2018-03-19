package com.activity

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.activity.di.IActivityProperties
import com.common.Constants

/**
 * Created by clickapps on 31/1/18.
 */
@Suppress("UNREACHABLE_CODE")
@MainThread
class Request {

    /***********************StartActivity */
    open class StartActivity(protected var param: ActivityParam) : IActivityProperties<StartActivity> {

        override fun bundle(bundle: Bundle): StartActivity {
            param.bundle = bundle
            return this
        }


        override fun flag(flag: Int): StartActivity {
            param.flag = flag
            return this
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun animation(enterAnim: Int, exitAnim: Int): StartActivity {
            param.enterAnim = enterAnim
            param.exitAnim = exitAnim
            param.isAnimation = true
            return this
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun activityCompactOption(activityOptionsCompat: ActivityOptionsCompat): StartActivity {
            param.activityOptionsCompat = activityOptionsCompat
            return this
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun sharedElements(vararg sharedElements: View): StartActivity {
            param.sharedElements = arrayOf(*sharedElements)
            return this
        }


        open fun build() {
            val activity = Constants.getTopActivity() ?: return
            if (param.context == null) {
                Log.e(javaClass.simpleName, "Context can't be null")
                return
            }
            val intent = Intent(param.context, param.uri)
            if (param.flag == 0) {
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            } else {
                intent.flags = param.flag
            }
            intent.putExtras(param.bundle)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                var bundle: Bundle? = Bundle()
                if (param.isAnimation) {
                    bundle = ActivityOptionsCompat
                            .makeCustomAnimation(param.context!!, param.enterAnim, param.exitAnim).toBundle()
                } else if (param.activityOptionsCompat != null) {
                    bundle = param.activityOptionsCompat!!.toBundle()
                } else if (param.sharedElements.isNotEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val len = param.sharedElements.size
                        val pairs = arrayOfNulls<Pair<View, String>>(len)
                        for (i in 0 until len) {
                            pairs[i] = Pair.create(param.sharedElements[i], param.sharedElements[i].transitionName)
                        }
                        bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs).toBundle()
                    } else {
                        bundle = Bundle()
                    }
                } else {
                    bundle = Bundle()
                }
                activity.startActivity(intent, bundle)
            } else {
                activity.startActivity(intent)
            }
        }
    }


    /**************************StartActivityFinish */
    class StartActivityFinish(param: ActivityParam) : StartActivity(param) {

        override fun build() {
            super.build()
            val activity = Constants.getTopActivity() ?: return
            activity.finish()
        }
    }

    /**************************StartActivityResult */
    open class StartActivityResult(param: ActivityParam) : StartActivity(param) {

        override fun build() {
            if (param.context == null) {
                Log.e(javaClass.simpleName, "Context can't be null")
                return
            }
            val intent = Intent(param.context, param.uri)
            intent.putExtras(param.bundle)
            val activity = Constants.getTopActivity() ?: return

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                var bundle: Bundle? = Bundle()
                if (param.isAnimation) {
                    bundle = ActivityOptionsCompat
                            .makeCustomAnimation(param.context!!, param.enterAnim, param.exitAnim).toBundle()
                } else if (param.activityOptionsCompat != null) {
                    bundle = param.activityOptionsCompat!!.toBundle()
                } else if (param.sharedElements.isNotEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val len = param.sharedElements.size
                        val pairs = arrayOfNulls<Pair<View, String>>(len)
                        for (i in 0 until len) {
                            pairs[i] = Pair.create(param.sharedElements[i], param.sharedElements[i].transitionName)
                        }
                        bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs).toBundle()
                    } else {
                        bundle = Bundle()
                    }
                } else {
                    bundle = Bundle()
                }
                activity.startActivityForResult(intent, param.requestCode, bundle)
            } else {
                activity.startActivityForResult(intent, param.requestCode)
            }
        }
    }

    /**************************StartActivityFinishResult */
    class StartActivityFinishResult(param: ActivityParam) : StartActivityResult(param) {

        override fun build() {
            super.build()
            val activity = Constants.getTopActivity() ?: return
            activity.finish()
        }
    }

    /*************************Finish */
    class Finish(internal var param: ActivityParam) {

        fun animation(enterAnim: Int, exitAnim: Int): Finish {
            param.enterAnim = enterAnim
            param.exitAnim = exitAnim
            param.isAnimation = true
            return this
        }

        fun build() {
            val activity = Constants.getTopActivity() ?: return
            if (activity is AppCompatActivity) {
                activity.supportFinishAfterTransition()
            } else {
                activity.finish()
            }
            if (param.isAnimation)
                activity.overridePendingTransition(param.enterAnim, param.exitAnim)
        }
    }
}
