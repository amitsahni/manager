package test.activity;

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

    @Override
    protected View initUI(LayoutInflater inflater, ViewGroup container) {
        if (mView == null) {
            mView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, null);
            ButterKnife.bind(this, mView);
            getBtn.setOnClickListener(this);
        }
        return mView;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            //setExitTransition(new Fade());
            FragmentManager.with(getContext())
                    .replace(android.R.id.content, SecondFragment.init(SecondFragment.class, new Bundle()))
                    .sharedElements(getBtn)
                   // .animation(0, android.R.animator.fade_in, 0, 0)
                    .backStack(true)
                    .build();
        } else {
            Log.i(getTag(), "No clickHandled");
        }
    }
}
