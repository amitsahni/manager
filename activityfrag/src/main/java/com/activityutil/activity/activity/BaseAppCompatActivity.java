package com.activityutil.activity.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.activityutil.activity.Constants;
import com.activityutil.activity.application.BaseApplication;
import com.activityutil.activity.broadcast.InternetBroadCastReceiver;
import com.activityutil.activity.broadcast.LanguageBroadCastReceiver;
import com.activityutil.activity.fragment.BaseFragment;
import com.activityutil.activity.interfaces.ConnectivityListener;
import com.activityutil.activity.interfaces.OnBackHandler;


/**
 * This is customized abstract activity class.
 *
 * @author amit.singh
 * @method initUI() method for initialize User Interface widgets
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements
//to identify child tasks and perform on activity itself
        View.OnClickListener, ConnectivityListener {

    /**
     * The Tag.
     */
    protected String TAG;
    private OnBackHandler mBackHandler;
    private BaseFragment mFragment = null;

    private LanguageBroadCastReceiver languageBroadCastReceiver;
    private IntentFilter filter = new IntentFilter(Constants.getActionBroadcastLanguageChanged());


//

    /**
     * Instantiates a new Base activity app compat.
     */
    protected BaseAppCompatActivity() {
        mBackHandler = null;
    }

    /**
     * This method is used to initialize UI of the layout. Called in onCreate()
     */
    protected abstract void initUI();


    /**
     * This method is used to show layout.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getLocalClassName();
        initUI();
        languageBroadCastReceiver = new LanguageBroadCastReceiver(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(languageBroadCastReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getApplication() instanceof BaseApplication) {
            InternetBroadCastReceiver internetBroadCastReceiver
                    = ((BaseApplication) getApplication()).getInternetBroadCastReceiver();
            internetBroadCastReceiver.addCallback(this);
        }
    }

    protected final Bundle getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = new Bundle();
        }
        return bundle;

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Sets back handler.
     *
     * @param mBackHandler the back handler
     */
    public void setBackHandler(OnBackHandler mBackHandler) {
        this.mBackHandler = mBackHandler;
    }

    /**
     * Gets back handler.
     *
     * @return the back handler
     */
    public OnBackHandler getBackHandler() {
        return mBackHandler;
    }

    @Override
    public void onBackPressed() {
        if (getBackHandler() != null) {
            getBackHandler().onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Sets mFragment when called another activity or application eg camera or gallery
     * After completed operation set this to null.
     *
     * @param fragment the mFragment
     */
    public void setOnActivityResultFragment(BaseFragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public void recreate() {
        super.recreate();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mFragment != null) {
            mFragment.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {

    }
}
