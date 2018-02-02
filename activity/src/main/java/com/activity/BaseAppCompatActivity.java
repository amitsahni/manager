package com.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.common.Constants;
import com.common.LanguageContextWrapper;
import com.common.application.BaseApplication;
import com.common.broadcast.InternetBroadCastReceiver;
import com.common.broadcast.LanguageBroadCastReceiver;
import com.common.interfaces.ConnectivityListener;

import java.util.Locale;


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
    private LanguageBroadCastReceiver languageBroadCastReceiver;
    private IntentFilter filter = new IntentFilter(Constants.getActionBroadcastLanguageChanged());


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
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(languageBroadCastReceiver);
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


    @Override
    public void onBackPressed() {
        if (getApplication() instanceof BaseApplication
                && ((BaseApplication) getApplication()).getBackHandler() != null) {
            ((BaseApplication) getApplication()).getBackHandler().onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void recreate() {
        super.recreate();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (getApplication() instanceof BaseApplication) {
            if (((BaseApplication) getApplication()).getFragment() != null) {
                ((BaseApplication) getApplication()).getFragment().onActivityResult(requestCode, resultCode, data);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageContextWrapper.wrap(newBase, Locale.getDefault().getLanguage()));
    }
}
