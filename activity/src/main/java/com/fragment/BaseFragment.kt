package com.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


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


    val bundle: Bundle by lazy {
        val b = if (arguments == null) {
            Bundle()
        } else {
            arguments
        }
        b!!
    }

    val fragmentActivity: AppCompatActivity? by lazy {
        var act: AppCompatActivity? = null
        activity?.let {
            if (it is AppCompatActivity)
                act = it
        }
        act
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return initUI(inflater, container)
    }

    open fun onPageSelected(pos: Int) {

    }

    override fun onClick(v: View) {

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

        @JvmStatic
        fun <T : androidx.fragment.app.Fragment> init(fragment: Class<T>, bundle: Bundle): androidx.fragment.app.Fragment {
            try {
                val f = fragment.newInstance()
                f.arguments = bundle
                return f
            } catch (e: java.lang.InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            return androidx.fragment.app.Fragment()
        }
    }
}
