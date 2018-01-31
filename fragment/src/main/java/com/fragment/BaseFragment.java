package com.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activityutil.activity.activity.BaseAppCompatActivity;
import com.activity.application.BaseApplication;
import com.activity.broadcast.InternetBroadCastReceiver;
import com.activity.interfaces.ConnectivityListener;
import com.activity.interfaces.OnBackHandler;


/**
 * This is customized abstract Fragment class. This will be the base fragment class for all the fragment classes within the
 * application that will have common functionality define below :
 *
 * @author amit.singh
 * @method initUI method for initialize User Interface widgets more.
 * @implements onClick listener for click event with in the class components
 */
public abstract class BaseFragment extends Fragment implements
        //click event listener
        View.OnClickListener,
        // Back press handle on Fragment
        OnBackHandler, ConnectivityListener {


    /**
     * The View.
     */
    protected View mView;
    private boolean mEnableBack = false;
    private boolean mEnableOnActivityResult = false;

    public static <T extends Fragment> Fragment init(@NonNull Class<T> fragment, Bundle bundle) {
        try {
            T f = fragment.newInstance();
            f.setArguments(bundle);
            return f;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Fragment();
    }

    protected Bundle getBundle() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        return bundle;

    }

    public FragmentActivity getFragmentActivity() {
        return (FragmentActivity) getActivity();
    }

    @Override
    public Context getContext() {
        return getFragmentActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initUI(inflater, container);
    }

    /**
     * Sets mFragment when called another activity or application eg camera or gallery
     */
    public void enableOnActivityResultFragment(boolean enableOnActivityResultFragment) {
        mEnableOnActivityResult = enableOnActivityResultFragment;
    }

    /**
     * Sets enable back handle.
     *
     * @param enableBack the enable back handle
     */
    public void enableBackPress(boolean enableBack) {
        this.mEnableBack = enableBack;
    }

    @Override
    public void onResume() {
        /**
         * Handle BackPress on Fragment.
         */
        if (getActivity() instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) getActivity()).setBackHandler(mEnableBack ? this : null);
            ((BaseAppCompatActivity) getActivity()).setOnActivityResultFragment(mEnableOnActivityResult ? this : null);
        }
        if (getActivity().getApplication() instanceof BaseApplication) {
            InternetBroadCastReceiver internetBroadCastReceiver
                    = ((BaseApplication) getActivity().getApplication()).getInternetBroadCastReceiver();
            internetBroadCastReceiver.addCallback(this);
        }
        super.onResume();
    }

    public void onPageSelected(int pos) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {

    }

    /**
     * Inflate View in this method back end called
     * {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *
     * @param inflater  the inflater
     * @param container the container
     * @return the mView
     */
    protected abstract View initUI(LayoutInflater inflater, ViewGroup container);
}
