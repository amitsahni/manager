package com.activity


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.view.View
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

    protected val TAG: String by lazy {
        localClassName
    }

    protected val bundle: Bundle
        get() {
            intent.extras?.apply {
                return this
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
        PreferenceManager.getDefaultSharedPreferences(this).getString("language", Locale.getDefault().language)?.let {
            LanguageContextWrapper.wrap(this, it)
        }
        super.onCreate(savedInstanceState)
        initUI()
        val languageLiveData = LanguageLiveData(this)
        languageLiveData.observeForever {
            if (it != null)
                recreate()
        }
        languageLiveData.observe(this, androidx.lifecycle.Observer {
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


    open fun onConnectivityChange(isConnectivity: Boolean) {
    }

    override fun attachBaseContext(base: Context) {
        val language = PreferenceManager.getDefaultSharedPreferences(base).getString("language", Locale.getDefault().language) ?: ""
        super.attachBaseContext(LanguageContextWrapper.wrap(base, language))
    }
}
