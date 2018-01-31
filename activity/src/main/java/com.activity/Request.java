package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.ActivityOptionsCompat;

import com.activity.di.IActivityProperties;
import com.base.Constants;

/**
 * Created by clickapps on 31/1/18.
 */
@SuppressWarnings("unchecked")
@MainThread
public class Request {

    /***********************StartActivity****************************/
    public static class StartActivity<T extends StartActivity> implements IActivityProperties<T> {
        ActivityParam param;

        public StartActivity(ActivityParam param) {
            this.param = param;
        }

        @Override
        public T bundle(Bundle bundle) {
            param.bundle = bundle;
            return (T) this;
        }


        @Override
        public T flag(int flag) {
            param.flag = flag;
            return (T) this;
        }

        @Override
        public T activityCompactOption(ActivityOptionsCompat activityOptionsCompat) {
            param.activityOptionsCompat = activityOptionsCompat;
            return (T) this;
        }

        public void build() {
            if (param.context == null) return;
            Intent intent = new Intent(param.context, param.uri);
            if (param.flag == 0) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
            } else {
                intent.setFlags(param.flag);
            }
            if (param.bundle != null) {
                intent.putExtras(param.bundle);
            }
            if (param.activityOptionsCompat != null
                    && Build.VERSION.SDK_INT >= 21) {
                param.context.startActivity(intent, param.activityOptionsCompat.toBundle());
            } else {
                param.context.startActivity(intent);
            }
        }
    }

    /**************************StartActivityFinish**************************/
    public static class StartActivityFinish<T extends StartActivityFinish> extends StartActivity<T> {

        public StartActivityFinish(ActivityParam param) {
            super(param);
        }

        @Override
        public void build() {
            super.build();
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            activity.finish();
        }
    }

    /**************************StartActivityResult****************************/
    public static class StartActivityResult<T extends StartActivityResult> extends StartActivity<T> {

        public StartActivityResult(ActivityParam param) {
            super(param);
        }

        @Override
        public void build() {
            if (param.context == null) return;
            Intent intent = new Intent(param.context, param.uri);
            if (param.bundle != null) {
                intent.putExtras(param.bundle);
            }
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            if (param.activityOptionsCompat != null
                    && Build.VERSION.SDK_INT >= 21) {
                activity.startActivityForResult(intent, param.requestCode, param.activityOptionsCompat.toBundle());
            } else {
                activity.startActivityForResult(intent, param.requestCode);
            }
        }
    }

    /**************************StartActivityFinishResult************************/
    public static class StartActivityFinishResult<T extends StartActivityFinishResult> extends StartActivityResult<T> {

        public StartActivityFinishResult(ActivityParam param) {
            super(param);
        }

        @Override
        public void build() {
            super.build();
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            activity.finish();
        }
    }

    /*************************Finish**********************************/
    public static class Finish<T extends Finish> {
        ActivityParam param;

        public Finish(ActivityParam param) {
            this.param = param;
        }

        public void build() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            activity.finish();
        }
    }
}
