package com.fragment;

import android.content.Context;
import android.support.annotation.NonNull;


/**
 * The type Fragment manager.
 */
public class FragmentManager {
    private static volatile FragmentManager sFragmentManager;

    private FragmentManager() {
        // private constructor
    }

    /**
     * Get fragment manager.
     *
     * @return the fragment manager
     */
    public static FragmentManager get() {
        if (sFragmentManager == null) {
            synchronized (FragmentManager.class) {
                if (sFragmentManager == null) {
                    sFragmentManager = new FragmentManager();
                }
            }
        }
        return sFragmentManager;
    }


    /**
     * With builder.
     *
     * @param context the context
     * @return the builder
     */
    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }
}
