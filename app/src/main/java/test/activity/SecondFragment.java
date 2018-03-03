package test.activity;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fragment.BaseFragment;
import com.fragment.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amit on 14/2/18.
 */

public class SecondFragment extends BaseFragment {
    View view;

    @Override
    protected View initUI(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_second, null);
            ButterKnife.bind(this, view);
        }
        return view;
    }
}
