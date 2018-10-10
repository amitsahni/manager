package com.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.activity.BaseAppCompatActivity
import com.common.application.BaseApplication
import com.common.broadcast.BackHandlerLiveData
import com.common.broadcast.FragmentOnActivityResultLiveData


/**
 * This is customized abstract Fragment class. This will be the base fragment class for all the fragment classes within the
 * application that will have common functionality define below :
 *
 * @author amit.singh
 * @method initUI method for initialize User Interface widgets more.
 * @implements onClick listener for click event with in the class components
 */
abstract class BaseFragment : Fragment(),
        //click event listener
        View.OnClickListener {


    private var enableBack = false
    private var enableOnActivityResult = false

    protected val bundle: Bundle
        get() {
            var bundle = arguments
            if (bundle == null) {
                bundle = Bundle()
            }
            return bundle
        }

    val fragmentActivity: FragmentActivity?
        get() {
            activity?.also {
                if (it is FragmentActivity) {
                    return it
                }
            }
            return null
        }
    var backHandler: BackHandlerLiveData? = null
    var onResultLiveData: FragmentOnActivityResultLiveData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        backHandler = BackHandlerLiveData(fragmentActivity!!.applicationContext)
        onResultLiveData = FragmentOnActivityResultLiveData(fragmentActivity!!.applicationContext)
        return initUI(inflater, container)
    }

    /**
     * Sets mFragment when called another activity or application eg camera or gallery
     */
    fun enableOnActivityResultFragment(enableOnActivityResultFragment: Boolean) {
        enableOnActivityResult = enableOnActivityResultFragment
        if (enableOnActivityResult) {
            onResultLiveData?.observe(this, Observer {
                val requestCode = it?.extras!!["requestCode"] as Int
                val resultCode = it.extras!!["resultCode"] as Int
                val data = it
                onActivityResult(requestCode, resultCode, data)
            })
        } else {
            onResultLiveData?.removeObservers(this)
        }
    }

    /**
     * Sets enable back handle.
     *
     * @param enableBack the enable back handle
     */
    fun enableBackPress(enableBack: Boolean) {
        this.enableBack = enableBack
        if (enableBack) {
            backHandler?.observe(this, Observer {
                onBackPressed()
            })
        } else {
            backHandler?.removeObservers(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (fragmentActivity is BaseAppCompatActivity) {
            (fragmentActivity as BaseAppCompatActivity).enableBackPress = this.enableBack
            (fragmentActivity as BaseAppCompatActivity).enableOnActivityResult = this.enableOnActivityResult
        }
    }

    open fun onPageSelected(pos: Int) {

    }

    override fun onClick(v: View) {

    }

    open fun onBackPressed() {

    }

    open fun onConnectivityChange(isConnectivity: Boolean) {

    }

    /**
     * Inflate View in this method back end called
     * [BaseFragment.onCreateView]
     *
     * @param inflater  the inflater
     * @param container the container
     * @return the mView
     */
    protected abstract fun initUI(inflater: LayoutInflater, container: ViewGroup?): View

    companion object {

        open fun <T : Fragment> init(fragment: Class<T>, bundle: Bundle): Fragment {
            try {
                val f = fragment.newInstance()
                f.arguments = bundle
                return f
            } catch (e: java.lang.InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            return Fragment()
        }
    }
}
