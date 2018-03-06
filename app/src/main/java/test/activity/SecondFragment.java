package test.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by amit on 14/2/18.
 */

public class SecondFragment extends BaseFragment {
    View view;

    @Override
    protected View initUI(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_second, null);
            ButterKnife.bind(this, view);
        }
        return view;
    }
}
