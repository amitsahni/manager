package com.fragment;

import android.app.Fragment;
import android.content.Context;


/**
 * The type Frag param.
 */
public class FragParam {

    Context context;
    int replaceId,
            enter = 0,
            exit = 0,
            popEnter = 0,
            popExit = 0;
    Fragment fragment;
    String tag;
    boolean enableAnimation = false,
            isBackStack = false;

}
