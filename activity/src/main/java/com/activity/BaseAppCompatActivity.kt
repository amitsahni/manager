package com.activity


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.annotation.Nullable
import android.support.annotation.RequiresApi
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.common.Constants
import com.common.LanguageContextWrapper
import com.common.broadcast.LanguageLiveData
import java.util.*


/**
 * This is customized abstract activity class.
 *
 * @author amit.singh
 * @method initUI() method for initialize User Interface widgets
 */
abstract class BaseAppCompatActivity : AppCompatActivity(),
        //to identify child tasks and perform on activity itself
        View.OnClickListener {

    protected var TAG: String = ""
    var enableBackPress = false
    var enableOnActivityResult = false
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
        val language = PreferenceManager.getDefaultSharedPreferences(this).getString("language", Locale.getDefault().language)
        LanguageContextWrapper.wrap(this, language)
        super.onCreate(savedInstanceState)
        TAG = localClassName
        initUI()
        val languageLiveData = LanguageLiveData(this)
        languageLiveData.observeForever {
            if (it != null)
                recreate()
        }
        languageLiveData.observe(this, android.arch.lifecycle.Observer {
            if (it != null) {
                @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                if (Build.VERSION.SDK_INT in 26..27) {
                    if (Locale.getDefault().language == "ar")
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
                    else
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
                }
            }
        })
    }

    override fun onClick(v: View) {
    }


    override fun onBackPressed() {
        if (enableBackPress) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(Constants.getActionBroadcastBackHandler()))
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (enableOnActivityResult) {
            val intent = Intent(Constants.getActionBroadcastOnResult())
            intent.putExtra("data", data)
            intent.putExtra("requestCode", requestCode)
            intent.putExtra("resultCode", resultCode)
            Handler().postDelayed({
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            }, 1)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    open fun onConnectivityChange(isConnectivity: Boolean) {
    }

    override fun attachBaseContext(base: Context) {
        val language = PreferenceManager.getDefaultSharedPreferences(base).getString("language", Locale.getDefault().language)
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
//            super.attachBaseContext(LanguageContextWrapper.wrap(base, language).baseContext)
//        } else {
//            super.attachBaseContext(base)
//        }
        super.attachBaseContext(LanguageContextWrapper.wrap(base, language))
    }
}
