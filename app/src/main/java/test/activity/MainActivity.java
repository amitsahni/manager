package test.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.activity.ActivityManager;
import com.activity.BaseAppCompatActivity;

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
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            ActivityManager.with(getApplicationContext())
                    .startActivity(SecondActivity.class)
                    .build();

        } else {
            Log.i(getLocalClassName(), "No clickHandled");
        }
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {
        Log.i(getLocalClassName(), "isConnectivity = " + isConnectivity);
    }
}
