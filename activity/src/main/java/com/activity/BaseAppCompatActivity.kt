package com.activity


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.Nullable
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
        super.onCreate(savedInstanceState)
        TAG = localClassName
        initUI()
        val languageLiveData = LanguageLiveData(this)
        languageLiveData.observeForever {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                if (Locale.getDefault().getLanguage() == "ar")
                    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                else
                    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
            recreate()
        }
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
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            super.attachBaseContext(LanguageContextWrapper.wrap(base, Locale.getDefault().language));
        } else {
            super.attachBaseContext(base);
        }
    }
}
