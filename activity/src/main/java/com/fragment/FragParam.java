package com.fragment;

import android.app.Fragment;
import android.content.Context;
import android.view.View;


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
    View[] sharedElements = null;

}
