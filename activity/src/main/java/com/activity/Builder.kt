package com.activity

import android.content.Context


/**
 * Created by clickapps on 1/11/17.
 */
/**
 * Instantiates a new Builder.
 *
 * @param context the context
 */
class Builder(context: Context) {

    private val param: ActivityParam = ActivityParam()

    init {
        param.context = context
    }


    fun startActivity(klass: Class<*>): Request.StartActivity {
        param.uri = klass
        return Request.StartActivity(param)
    }

    fun startActivityFinish(klass: Class<*>): Request.StartActivityFinish {
        param.uri = klass
        return Request.StartActivityFinish(param)
    }

    fun startActivityForResult(klass: Class<*>, requestCode: Int): Request.StartActivityResult {
        param.requestCode = requestCode
        param.uri = klass
        return Request.StartActivityResult(param)
    }

    fun startActivityForResultFinish(klass: Class<*>, requestCode: Int): Request.StartActivityFinishResult {
        param.requestCode = requestCode
        param.uri = klass
        return Request.StartActivityFinishResult(param)
    }

    fun finish(): Request.Finish {
        return Request.Finish(param)
    }

}
