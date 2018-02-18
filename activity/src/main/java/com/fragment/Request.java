package com.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;

import com.common.Constants;
import com.fragment.di.IFragmentProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 31/1/18.
 */

@SuppressWarnings("ALL")
public class Request {
    /*************************Replace*************************/
    public static class Replace<T extends Replace> implements IFragmentProperties<T> {
        FragParam param;

        public Replace(FragParam param) {
            this.param = param;
        }

        public T backStack(boolean isBackStack) {
            param.isBackStack = isBackStack;
            return (T) this;
        }

        public T sharedElements(View... sharedElements) {
            param.sharedElements = sharedElements;
            return (T) this;
        }

        @Override
        public T animation(@AnimatorRes int enter, @AnimatorRes int exit,
                           @AnimatorRes int popEnter, @AnimatorRes int popExit) {
            param.enter = enter;
            param.exit = exit;
            param.popEnter = popEnter;
            param.popExit = popExit;
            param.enableAnimation = true;
            return (T) this;
        }

        public void build() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            FragmentTransaction ft = activity.getFragmentManager()
                    .beginTransaction();
            if (param.enableAnimation) {
                ft.setCustomAnimations(param.enter, param.exit,
                        param.popEnter, param.popExit);
            }
            if (param.sharedElements != null
                    && param.sharedElements.length > 0) {
                for (View view : param.sharedElements) {
                    ft.addSharedElement(view, view.getTransitionName());
                }
                TransitionSet enterTransitionSet = new TransitionSet();
                enterTransitionSet.addTransition(TransitionInflater.from(param.context).inflateTransition(android.R.transition.move));
                enterTransitionSet.setDuration(800);
                param.fragment.setSharedElementEnterTransition(enterTransitionSet);
                param.fragment.setEnterTransition(new ChangeImageTransform());
            }
            ft.replace(param.replaceId, param.fragment);
            if (param.isBackStack) {
                String backStackName = null;
                Fragment fragment = activity.getFragmentManager().findFragmentById(param.replaceId);
                if (fragment != null) {
                    backStackName = fragment.getClass().getSimpleName();
                }
                ft.addToBackStack(backStackName);
            }
            ft.commit();
        }
    }

    public static class Pop<T extends Pop> implements IFragmentProperties<T> {
        FragParam param;

        public Pop(FragParam param) {
            this.param = param;
        }

        @Override
        public T animation(int enter, int exit, int popEnter, int popExit) {
            param.enter = enter;
            param.exit = exit;
            param.popEnter = popEnter;
            param.popExit = popExit;
            param.enableAnimation = true;
            return (T) this;
        }

        public void build() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            FragmentManager fragmentManager = activity.getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            int count = fragmentManager.getBackStackEntryCount();
            if (count > 0) {
                if (param.enableAnimation) {
                    ft.setCustomAnimations(param.enter, param.exit,
                            param.popEnter, param.popExit);
                }
                ft.remove(fragmentManager.findFragmentById(param.replaceId));
                ft.commit();
                fragmentManager.popBackStackImmediate();
            }

        }
    }

    public static class PopTag<T extends PopTag> implements IFragmentProperties<T> {
        FragParam param;

        public PopTag(FragParam param) {
            this.param = param;
        }


        @Override
        public T animation(int enter, int exit, int popEnter, int popExit) {
            param.enter = enter;
            param.exit = exit;
            param.popEnter = popEnter;
            param.popExit = popExit;
            param.enableAnimation = true;
            return (T) this;
        }

        public void build() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            FragmentManager fragmentManager = activity.getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            int count = fragmentManager.getBackStackEntryCount();
            if (count > 0) {
                if (param.enableAnimation) {
                    ft.setCustomAnimations(param.enter, param.exit,
                            param.popEnter, param.popExit);
                }
                ft.remove(fragmentManager.findFragmentByTag(param.tag));
                ft.commit();
                fragmentManager.popBackStackImmediate();
            }

        }
    }

    public static class Restart<T extends Restart> {
        FragParam param;

        public Restart(FragParam param) {
            this.param = param;
        }

        public void build() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return;
            FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
            ft.detach(param.fragment);
            ft.attach(param.fragment);
            ft.commit();
        }
    }

    public static class FragUtil<T extends FragUtil> {
        FragParam param;

        public FragUtil(FragParam param) {
            this.param = param;
        }

        @Nullable
        public Fragment getTopFragmentByTag() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return null;
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment fragment = null;
            for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
                fragment = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(entry)
                        .getName());
            }
            return fragment;
        }

        @Nullable
        public Fragment getTopFragmentById() {
            Activity activity = Constants.getTopActivity();
            if (activity == null) return null;
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment fragment = null;
            for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
                fragment = fragmentManager.findFragmentById(fragmentManager.getBackStackEntryAt(entry)
                        .getId());
            }
            return fragment;
        }

        @Nullable
        public List<String> getStackList() {
            List<String> list = new ArrayList<>();
            Activity activity = Constants.getTopActivity();
            if (activity == null) return list;
            FragmentManager fm = activity.getFragmentManager();
            for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
                list.add(fm.getBackStackEntryAt(entry).getName());
            }
            return list;
        }

        @Nullable
        public Fragment getFragment(@IdRes int id) {
            List<String> list = new ArrayList<>();
            Activity activity = Constants.getTopActivity();
            if (activity == null) return null;
            FragmentManager fm = activity.getFragmentManager();
            return fm.findFragmentById(id);
        }

        @Nullable
        public Fragment getFragment(@NonNull String tag) {
            List<String> list = new ArrayList<>();
            Activity activity = Constants.getTopActivity();
            if (activity == null) return null;
            FragmentManager fm = activity.getFragmentManager();
            return fm.findFragmentByTag(tag);
        }
    }
}
