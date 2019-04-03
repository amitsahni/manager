package test.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.fragment.BaseFragment


/**
 * Created by amit on 14/2/18.
 */

open class SecondFragment : BaseFragment() {
    internal var view: View? = null

    @BindView(R.id.button)
    var getBtn: Button? = null

    override fun initUI(inflater: LayoutInflater, container: ViewGroup?): View {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.activity_second, null)
            ButterKnife.bind(this, view!!)
            view?.findViewById<View>(R.id.button)?.setOnClickListener(this)
        }
        return view!!
    }

    override fun onClick(v: View) {
        val id = view?.getId()
        if (id == R.id.button) {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), 100)
        }
        if (context?.applicationContext is AppApplication) {
            (context?.applicationContext as AppApplication).gallery(fragmentActivity)
        }
    }
}
