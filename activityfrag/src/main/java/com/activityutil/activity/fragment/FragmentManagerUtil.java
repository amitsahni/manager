package com.activityutil.activity.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Fragment manager util.
 */
public class FragmentManagerUtil {

    public FragmentManagerUtil() {
        // Default Constructor
    }

    /**
     * Perform task.
     *
     * @param fragParam the frag param
     */
    static void performTask(FragParam fragParam) {
        FragmentTransaction ft = fragParam.getContext().getFragmentManager()
                .beginTransaction();
        if (fragParam.isEnableAnimation()) {
            ft.setCustomAnimations(fragParam.getEnter(), fragParam.getExit(), fragParam.getPopEnter(), fragParam.getPopExit());
        }
        fragParam.getFragType().execute(fragParam, ft);
    }

    /**
     * Replace.
     *
     * @param fragParam the frag param
     * @param ft        the ft
     */
    static void replace(FragParam fragParam, FragmentTransaction ft) {
        if (!TextUtils.isEmpty(fragParam.getTag())) {
            ft.replace(fragParam.getReplaceId(), fragParam.getFragment(), fragParam.getTag());
        } else {
            ft.replace(fragParam.getReplaceId(), fragParam.getFragment());
        }
        if (fragParam.isBackStack()) {
            ft.addToBackStack(fragParam.getTag());
        }
        ft.commit();
    }

    /**
     * Pop.
     *
     * @param fragParam the frag param
     * @param ft        the ft
     */
    static void pop(FragParam fragParam, FragmentTransaction ft) {
        if (fragParam.getContext().getFragmentManager().getBackStackEntryCount() > 0) {
            ft.remove(fragParam.getContext().getFragmentManager()
                    .findFragmentById(fragParam.getReplaceId()));
            ft.commit();
            fragParam.getContext().getFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * Pop tag.
     *
     * @param fragParam the frag param
     * @param ft        the ft
     */
    static void popTag(FragParam fragParam, FragmentTransaction ft) {
        if (fragParam.getContext().getFragmentManager().getBackStackEntryCount() > 0) {
            ft.remove(fragParam.getContext().getFragmentManager()
                    .findFragmentByTag(fragParam.getTag()));
            ft.commit();
            fragParam.getContext().getFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * Restart.
     *
     * @param fragParam the frag param
     * @param ft        the ft
     */
    static void restart(FragParam fragParam, FragmentTransaction ft) {
        try {
            Fragment fragment = FragUtil.getFragment(fragParam.getContext(), fragParam.getReplaceId());
            if (fragment != null) {
                ft.detach(fragment);
                ft.attach(fragment);
                ft.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear.
     *
     * @param fragParam the frag param
     */
    static void clear(FragParam fragParam) {
        fragParam.getContext().getFragmentManager()
                .popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * This method is used to get the top fragmnet on the stack
     *
     * @param activity the activity
     * @return {@link Fragment}
     */
    public static Fragment getTopFragment(@NonNull FragmentActivity activity) {
        if (activity == null)
            return null;
        FragmentManager fm = activity.getFragmentManager();
        Fragment fragment = null;
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            fragment = fm.findFragmentByTag(fm.getBackStackEntryAt(entry)
                    .getName());
        }
        return fragment;
    }

    /**
     * This method is used to get List of backstack fragments
     *
     * @param activity the activity
     * @return {@link List}
     */
    public static List<String> getStackList(@NonNull FragmentActivity activity) {
        List<String> stackList = new ArrayList<>();
        stackList.clear();
        if (activity == null)
            return stackList;
        FragmentManager fm = activity.getFragmentManager();
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            stackList.add(fm.getBackStackEntryAt(entry).getName());
        }
        return stackList;
    }

    /**
     * This method is used to get the fragment
     *
     * @param activity the activity
     * @param id       set UniqueId
     * @return {@link Fragment}
     */
    public static Fragment getFragment(@NonNull FragmentActivity activity, int id) {
        if (activity == null)
            return null;
        return activity.getFragmentManager().findFragmentById(id);
    }

    /**
     * This method is used to get the fragment
     *
     * @param activity the activity
     * @param tag      set UniqueTag
     * @return {@link Fragment}
     */
    public static Fragment getFragment(@NonNull FragmentActivity activity, @NonNull String tag) {
        if (activity == null)
            return null;
        return activity.getFragmentManager().findFragmentByTag(tag);
    }

}
