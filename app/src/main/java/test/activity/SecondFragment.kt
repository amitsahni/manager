package test.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.fragment.BaseFragment

/**
 * Created by amit on 14/2/18.
 */

open class SecondFragment : BaseFragment() {
    internal var view: View? = null

    override fun initUI(inflater: LayoutInflater, container: ViewGroup?): View {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.activity_second, null)
            ButterKnife.bind(this, view!!)
        }
        return view!!
    }


}
