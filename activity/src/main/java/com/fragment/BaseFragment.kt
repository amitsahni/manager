package com.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.common.application.BaseApplication
import com.common.interfaces.ConnectivityListener
import com.common.interfaces.OnBackHandler


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
        View.OnClickListener,
        // Back press handle on Fragment
        OnBackHandler, ConnectivityListener {


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return initUI(inflater, container)
    }

    /**
     * Sets mFragment when called another activity or application eg camera or gallery
     */
    fun enableOnActivityResultFragment(enableOnActivityResultFragment: Boolean) {
        enableOnActivityResult = enableOnActivityResultFragment
    }

    /**
     * Sets enable back handle.
     *
     * @param enableBack the enable back handle
     */
    fun enableBackPress(enableBack: Boolean) {
        this.enableBack = enableBack
    }

    override fun onResume() {
        activity?.application.also {
            if (it is BaseApplication) {
                it.setOnActivityResultFragment(if (enableOnActivityResult) this else null)
                it.backHandler = if (enableBack) this else null
                val internetBroadCastReceiver = it.internetBroadCastReceiver
                internetBroadCastReceiver?.addCallback(this)
            }
        }
        super.onResume()
    }

    open fun onPageSelected(pos: Int) {

    }

    override fun onClick(v: View) {

    }

    override fun onBackPressed() {

    }

    override fun onConnectivityChange(isConnectivity: Boolean) {

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
