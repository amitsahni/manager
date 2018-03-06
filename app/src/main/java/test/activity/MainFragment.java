package test.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.activity.ActivityManager;
import com.fragment.BaseFragment;
import com.fragment.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amit on 14/2/18.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.button)
    Button getBtn;
    View view;

    @Override
    protected View initUI(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
            ButterKnife.bind(this, view);
            getBtn.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            //setExitTransition(new Fade());
            FragmentManager.with(getActivity())
                    .replace(android.R.id.content, SecondFragment.Companion.init(SecondFragment.class, new Bundle()))
                    // .animation(0, android.R.animator.fade_in, 0, 0)
                    .backStack(true)
                    .build();
        } else {
            Log.i(getTag(), "No clickHandled");
        }
    }
}
