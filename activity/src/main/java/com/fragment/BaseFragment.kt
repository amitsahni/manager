package com.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


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



    protected val bundle: Bundle
        get() {
            arguments?.let {
                return it
            }
            return Bundle()
        }

    val fragmentActivity: AppCompatActivity?
        get() {
            activity?.let {
                if (it is AppCompatActivity)
                    return it
            }
            return null
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
