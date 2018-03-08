package com.activity


import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.common.Constants
import com.common.LanguageContextWrapper
import com.common.application.BaseApplication
import com.common.broadcast.LanguageBroadCastReceiver
import com.common.interfaces.ConnectivityListener
import java.util.*


/**
 * This is customized abstract activity class.
 *
 * @author amit.singh
 * @method initUI() method for initialize User Interface widgets
 */
abstract class BaseAppCompatActivity : AppCompatActivity(),
        //to identify child tasks and perform on activity itself
        View.OnClickListener, ConnectivityListener {

    protected var TAG: String = ""
    private var languageBroadCastReceiver: LanguageBroadCastReceiver? = null
    private val filter = IntentFilter(Constants.getActionBroadcastLanguageChanged())

    protected val bundle: Bundle
        get() {
            intent.extras?.apply {
                return intent.extras
            }
            return Bundle()

        }


    /**
     * This method is used to initialize UI of the layout. Called in onCreate()
     */
    protected abstract fun initUI()

    /**
     * This method is used to show layout.
     */
    public override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = localClassName
        initUI()
        languageBroadCastReceiver = LanguageBroadCastReceiver(this)
        LocalBroadcastManager.getInstance(this).registerReceiver(languageBroadCastReceiver!!, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(languageBroadCastReceiver!!)
    }

    override fun onResume() {
        super.onResume()
        if (application is BaseApplication) {
            val internetBroadCastReceiver = (application as BaseApplication).internetBroadCastReceiver
            internetBroadCastReceiver?.addCallback(this)
        }
    }

    override fun onClick(v: View) {
    }


    override fun onBackPressed() {
        if (application is BaseApplication) {
            (application as BaseApplication).also {
                if (it.backHandler != null) {
                    it.backHandler?.onBackPressed()
                } else {
                    super.onBackPressed()
                }
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun recreate() {
        super.recreate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (application is BaseApplication) {
            (application as BaseApplication).fragment?.also {
                it.onActivityResult(requestCode, resultCode, data)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onConnectivityChange(isConnectivity: Boolean) {
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageContextWrapper.wrap(newBase, Locale.getDefault().language))
    }
}
