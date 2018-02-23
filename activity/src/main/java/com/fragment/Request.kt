package com.fragment

import android.app.Fragment
import android.os.Build
import android.support.annotation.AnimatorRes
import android.support.annotation.IdRes
import android.support.annotation.RequiresApi
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.view.View
import com.common.Constants
import com.fragment.di.IFragmentProperties
import java.util.*

/**
 * Created by clickapps on 31/1/18.
 */
class Request {
    /*************************Replace */
    class Replace(internal var param: FragParam) : IFragmentProperties<Replace> {

        fun backStack(isBackStack: Boolean): Replace {
            param.isBackStack = isBackStack
            return this
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        fun sharedElements(vararg sharedElements: View): Replace {
            param.sharedElements = arrayOf(*sharedElements)
            return this
        }

        override fun animation(@AnimatorRes enter: Int, @AnimatorRes exit: Int,
                               @AnimatorRes popEnter: Int, @AnimatorRes popExit: Int): Replace {
            param.enter = enter
            param.exit = exit
            param.popEnter = popEnter
            param.popExit = popExit
            param.enableAnimation = true
            return this
        }

        fun build() {
            val activity = Constants.getTopActivity() ?: return
            val ft = activity.fragmentManager
                    .beginTransaction()
            if (param.enableAnimation) {
                ft.setCustomAnimations(param.enter, param.exit,
                        param.popEnter, param.popExit)
            }
            if (param.sharedElements.isNotEmpty()
                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                for (view in param.sharedElements!!) {
                    ft.addSharedElement(view, view.transitionName)
                }
                val enterTransitionSet = TransitionSet()
                enterTransitionSet.addTransition(TransitionInflater.from(param.context).inflateTransition(android.R.transition.move))
                enterTransitionSet.duration = 800
                param.fragment!!.sharedElementEnterTransition = enterTransitionSet
            }
            ft.replace(param.replaceId, param.fragment)
            if (param.isBackStack) {
                var backStackName: String? = null
                val fragment = activity.fragmentManager.findFragmentById(param.replaceId)
                if (fragment != null) {
                    backStackName = fragment.javaClass.simpleName
                }
                ft.addToBackStack(backStackName)
            }
            ft.commit()
        }
    }

    class Pop(internal var param: FragParam) : IFragmentProperties<Pop> {

        override fun animation(enter: Int, exit: Int, popEnter: Int, popExit: Int): Pop {
            param.enter = enter
            param.exit = exit
            param.popEnter = popEnter
            param.popExit = popExit
            param.enableAnimation = true
            return this
        }

        fun build() {
            val activity = Constants.getTopActivity() ?: return
            val fragmentManager = activity.fragmentManager
            val ft = fragmentManager.beginTransaction()
            val count = fragmentManager.backStackEntryCount
            if (count > 0) {
                if (param.enableAnimation) {
                    ft.setCustomAnimations(param.enter, param.exit,
                            param.popEnter, param.popExit)
                }
                ft.remove(fragmentManager.findFragmentById(param.replaceId))
                ft.commit()
                fragmentManager.popBackStackImmediate()
            }

        }
    }

    class PopTag(internal var param: FragParam) : IFragmentProperties<PopTag> {


        override fun animation(enter: Int, exit: Int, popEnter: Int, popExit: Int): PopTag {
            param.enter = enter
            param.exit = exit
            param.popEnter = popEnter
            param.popExit = popExit
            param.enableAnimation = true
            return this
        }

        fun build() {
            val activity = Constants.getTopActivity() ?: return
            val fragmentManager = activity.fragmentManager
            val ft = fragmentManager.beginTransaction()
            val count = fragmentManager.backStackEntryCount
            if (count > 0) {
                if (param.enableAnimation) {
                    ft.setCustomAnimations(param.enter, param.exit,
                            param.popEnter, param.popExit)
                }
                ft.remove(fragmentManager.findFragmentByTag(param.tag))
                ft.commit()
                fragmentManager.popBackStackImmediate()
            }

        }
    }

    class Restart(internal var param: FragParam) {

        fun build() {
            val activity = Constants.getTopActivity() ?: return
            val ft = activity.fragmentManager.beginTransaction()
            ft.detach(param.fragment)
            ft.attach(param.fragment)
            ft.commit()
        }
    }

    class FragUtil(internal var param: FragParam) {

        val topFragmentByTag: Fragment?
            get() {
                val activity = Constants.getTopActivity() ?: return null
                val fragmentManager = activity.fragmentManager
                var fragment: Fragment? = null
                for (entry in 0 until fragmentManager.backStackEntryCount) {
                    fragment = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(entry)
                            .name)
                }
                return fragment
            }

        val topFragmentById: Fragment?
            get() {
                val activity = Constants.getTopActivity() ?: return null
                val fragmentManager = activity.fragmentManager
                var fragment: Fragment? = null
                for (entry in 0 until fragmentManager.backStackEntryCount) {
                    fragment = fragmentManager.findFragmentById(fragmentManager.getBackStackEntryAt(entry)
                            .id)
                }
                return fragment
            }

        val stackList: List<String>?
            get() {
                val list = ArrayList<String>()
                val activity = Constants.getTopActivity() ?: return list
                val fm = activity.fragmentManager
                for (entry in 0 until fm.backStackEntryCount) {
                    list.add(fm.getBackStackEntryAt(entry).name)
                }
                return list
            }

        fun getFragment(@IdRes id: Int): Fragment? {
            val list = ArrayList<String>()
            val activity = Constants.getTopActivity() ?: return null
            val fm = activity.fragmentManager
            return fm.findFragmentById(id)
        }

        fun getFragment(tag: String): Fragment? {
            val list = ArrayList<String>()
            val activity = Constants.getTopActivity() ?: return null
            val fm = activity.fragmentManager
            return fm.findFragmentByTag(tag)
        }
    }
}
