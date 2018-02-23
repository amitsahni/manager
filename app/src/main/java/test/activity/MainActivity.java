package test.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.activity.ActivityManager;
import com.activity.BaseAppCompatActivity;
import com.fragment.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by clickapps on 31/8/17.
 */

public class MainActivity extends BaseAppCompatActivity {
    @BindView(R.id.button)
    Button getBtn;


    @Override
    protected void initUI() {
        setContentView(R.layout.activity_frag);
        ButterKnife.bind(this);
//        getBtn.setOnClickListener(this);
        FragmentManager.with(this)
                .replace(android.R.id.content, MainFragment.Companion.init(MainFragment.class, new Bundle()))
                .build();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            ActivityManager.with(getApplicationContext())
                    .startActivity(SecondActivity.class)
                    .build();
            FragmentManager.with(this)
                    .utils()
                    .getTopFragmentByTag();
        } else {
            Log.i(getLocalClassName(), "No clickHandled");
        }
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {
        Log.i(getLocalClassName(), "isConnectivity = " + isConnectivity);
    }
}
