package com.fragment

import android.app.Fragment
import android.content.Context
import android.support.annotation.IdRes

/**
 * Created by clickapps on 1/11/17.
 */

class Builder(context: Context) {
    private val param: FragParam

    init {
        param = FragParam()
        param.context = context
    }

    fun replace(@IdRes replaceId: Int, fragment: Fragment): Request.Replace {
        param.replaceId = replaceId
        param.fragment = fragment
        return Request.Replace(param)
    }

    fun pop(@IdRes replaceId: Int): Request.Pop {
        param.replaceId = replaceId
        return Request.Pop(param)
    }

    fun pop(tag: String): Request.PopTag {
        param.tag = tag
        return Request.PopTag(param)
    }

    fun restart(fragment: Fragment): Request.Restart {
        param.fragment = fragment
        return Request.Restart(param)
    }

    fun utils(): Request.FragUtil {
        return Request.FragUtil(param)
    }

}
