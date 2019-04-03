package test.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.activity.BaseAppCompatActivity;

import java.util.Arrays;

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getBtn.setOnClickListener(this);
        String[] a = {"1","2"};
        String value = Arrays.toString(a);
        Log.i(getLocalClassName(),"Value = "+value);
//        FragmentManager.with(this)
//                .replace(android.R.id.content, MainFragment.Companion.init(MainFragment.class, new Bundle()))
//                .build();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
        } else {
            Log.i(getLocalClassName(), "No clickHandled");
        }
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {
        Log.i(getLocalClassName(), "isConnectivity = " + isConnectivity);
    }
}
