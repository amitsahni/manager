package com.fragment;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

/**
 * Created by clickapps on 1/11/17.
 */

public class Builder {
    private FragParam param;

    public Builder(@NonNull Context context) {
        param = new FragParam();
        param.context = context;
    }

    public Request.Replace replace(@IdRes int replaceId, @NonNull Fragment fragment) {
        param.replaceId = replaceId;
        param.fragment = fragment;
        return new Request.Replace(param);
    }

    public Request.Pop pop(@IdRes int replaceId) {
        param.replaceId = replaceId;
        return new Request.Pop(param);
    }

    public Request.PopTag pop(String tag) {
        param.tag = tag;
        return new Request.PopTag(param);
    }

    public Request.Restart restart(@NonNull Fragment fragment) {
        param.fragment = fragment;
        return new Request.Restart(param);
    }

    public Request.FragUtil utils() {
        return new Request.FragUtil(param);
    }

}
