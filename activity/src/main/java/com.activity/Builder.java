package com.activity;

import android.content.Context;
import android.support.annotation.NonNull;


/**
 * Created by clickapps on 1/11/17.
 */

public class Builder {

    private ActivityParam param;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Context context) {
        param = new ActivityParam();
        param.context = context;
    }


    public Request.StartActivity startActivity(Class<?> klass) {
        param.uri = klass;
        return new Request.StartActivity(param);
    }

    public Request.StartActivityFinish startActivityFinish(Class<?> klass) {
        param.uri = klass;
        return new Request.StartActivityFinish(param);
    }

    public Request.StartActivityResult startActivityForResult(Class<?> klass, int requestCode) {
        param.requestCode = requestCode;
        param.uri = klass;
        return new Request.StartActivityResult(param);
    }

    public Request.StartActivityFinishResult startActivityForResultFinish(Class<?> klass, int requestCode) {
        param.requestCode = requestCode;
        param.uri = klass;
        return new Request.StartActivityFinishResult(param);
    }

    public Request.Finish finish() {
        return new Request.Finish(param);
    }
}
