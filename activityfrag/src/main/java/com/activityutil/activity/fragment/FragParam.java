package com.activityutil.activity.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


/**
 * The type Frag param.
 */
public class FragParam {

    /**
     * The Context.
     */
    private FragmentActivity context;
    /**
     * The Replace id.
     */
    private int replaceId, /**
     * The Enter.
     */
    enter = 0, /**
     * The Exit.
     */
    exit = 0, /**
     * The Pop enter.
     */
    popEnter = 0, /**
     * The Pop exit.
     */
    popExit = 0;
    /**
     * The Fragment.
     */
    private Fragment fragment;
    /**
     * The Tag.
     */
    private String tag;
    /**
     * The Frag type.
     */
    private FragType fragType = FragType.REPLACE;

    /**
     * The Enable animation.
     */
    private boolean enableAnimation = false, /**
     * The Is back stack.
     */
    isBackStack = false;

    /**
     * The enum Frag type.
     */
    public enum FragType {
        /**
         * The Replace.
         */
        REPLACE {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.replace(fragParam, ft);
            }
        }, /**
         * The Pop.
         */
        POP {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.pop(fragParam, ft);
            }
        }, /**
         * The Pop tag.
         */
        POP_TAG {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.popTag(fragParam, ft);
            }
        }, /**
         * The Restart.
         */
        RESTART {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.restart(fragParam, ft);
            }
        }, /**
         * The Clear.
         */
        CLEAR {
            @Override
            public void execute(FragParam fragParam, FragmentTransaction ft) {
                FragmentManagerUtil.clear(fragParam);
            }
        };

        /**
         * Execute.
         *
         * @param fragParam the frag param
         * @param ft        the ft
         */
        public abstract void execute(FragParam fragParam, FragmentTransaction ft);
    }

    public FragmentActivity getContext() {
        return context;
    }

    public int getReplaceId() {
        return replaceId;
    }

    public int getEnter() {
        return enter;
    }

    public int getExit() {
        return exit;
    }

    public int getPopEnter() {
        return popEnter;
    }

    public int getPopExit() {
        return popExit;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTag() {
        return tag;
    }

    public FragType getFragType() {
        return fragType;
    }

    public boolean isEnableAnimation() {
        return enableAnimation;
    }

    public boolean isBackStack() {
        return isBackStack;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private FragParam mFragParam;

        /**
         * Instantiates a new Builder.
         *
         * @param context   the context
         * @param replaceId the replace id
         */
        public Builder(@NonNull FragmentActivity context, @IdRes int replaceId) {
            mFragParam = new FragParam();
            mFragParam.context = context;
            mFragParam.replaceId = replaceId;
        }

        /**
         * Fragment builder.
         *
         * @param fragment the fragment
         * @return the builder
         */
        public Builder fragment(@NonNull Fragment fragment) {
            mFragParam.fragment = fragment;
            return this;
        }

        /**
         * Tag builder.
         *
         * @param tag the tag
         * @return the builder
         */
        public Builder tag(@NonNull String tag) {
            mFragParam.tag = tag;
            return this;
        }

        /**
         * Enable animation builder.
         *
         * @param enableAnimation the enable animation
         * @return the builder
         */
        public Builder enableAnimation(boolean enableAnimation) {
            mFragParam.enableAnimation = enableAnimation;
            return this;
        }

        /**
         * Type builder.
         *
         * @param fragType the frag type
         * @return the builder
         */
        public Builder type(@NonNull FragParam.FragType fragType) {
            mFragParam.fragType = fragType;
            return this;
        }

        /**
         * Back stack builder.
         *
         * @param isBackStack the is back stack
         * @return the builder
         */
        public Builder backStack(boolean isBackStack) {
            mFragParam.isBackStack = isBackStack;
            return this;
        }

        /**
         * Animation builder.
         *
         * @param enter    the enter
         * @param exit     the exit
         * @param popEnter the pop enter
         * @param popExit  the pop exit
         * @return the builder
         */
        public Builder animation(int enter, int exit, int popEnter, int popExit) {
            mFragParam.enter = enter;
            mFragParam.exit = exit;
            mFragParam.popEnter = popEnter;
            mFragParam.popExit = popExit;
            return this;
        }


        /**
         * Build.
         */
        public void build() {
            if (mFragParam.context == null) {
                Log.e(getClass().getSimpleName(), "Context is null");
                return;
            }
            FragmentManagerUtil.performTask(mFragParam);
        }
    }
}
