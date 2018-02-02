package com.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.activity.di.IActivityProperties;
import com.common.Constants;

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
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public T animation(int enterAnim, int exitAnim) {
            param.enterAnim = enterAnim;
            param.exitAnim = exitAnim;
            param.isAnimation = true;
            return (T) this;
        }

        @Override
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public T activityCompactOption(ActivityOptionsCompat activityOptionsCompat) {
            param.activityOptionsCompat = activityOptionsCompat;
            return (T) this;
        }

        @Override
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public T sharedElements(View[] sharedElements) {
            param.sharedElements = sharedElements;
            return (T) this;
        }

        public void build() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Bundle bundle = new Bundle();
                if (param.isAnimation) {
                    bundle = ActivityOptionsCompat
                            .makeCustomAnimation(param.context, param.enterAnim, param.exitAnim).toBundle();
                } else if (param.sharedElements != null
                        && param.sharedElements.length > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int len = param.sharedElements.length;
                        @SuppressWarnings("unchecked")
                        Pair<View, String>[] pairs = new Pair[len];
                        for (int i = 0; i < len; i++) {
                            pairs[i] = Pair.create(param.sharedElements[i], param.sharedElements[i].getTransitionName());
                        }
                        bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs).toBundle();
                    } else {
                        bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, null, null).toBundle();
                    }
                } else if (param.activityOptionsCompat != null) {
                    bundle = param.activityOptionsCompat.toBundle();
                }
                activity.startActivity(intent, bundle);
            } else {
                activity.startActivity(intent);
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Bundle bundle = new Bundle();
                if (param.isAnimation) {
                    bundle = ActivityOptionsCompat
                            .makeCustomAnimation(param.context, param.enterAnim, param.exitAnim).toBundle();
                } else if (param.sharedElements.length > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int len = param.sharedElements.length;
                        @SuppressWarnings("unchecked")
                        Pair<View, String>[] pairs = new Pair[len];
                        for (int i = 0; i < len; i++) {
                            pairs[i] = Pair.create(param.sharedElements[i], param.sharedElements[i].getTransitionName());
                        }
                        bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs).toBundle();
                    } else {
                        bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, null, null).toBundle();
                    }
                } else if (param.activityOptionsCompat != null) {
                    bundle = param.activityOptionsCompat.toBundle();
                }
                activity.startActivityForResult(intent, param.requestCode, bundle);
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

        public T animation(int enterAnim, int exitAnim) {
            param.enterAnim = enterAnim;
            param.exitAnim = exitAnim;
            param.isAnimation = true;
            return (T) this;
        }

        public void build() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).supportFinishAfterTransition();
            } else {
                activity.finish();
            }
            if (param.isAnimation)
                activity.overridePendingTransition(param.enterAnim, param.exitAnim);
        }
    }
}
