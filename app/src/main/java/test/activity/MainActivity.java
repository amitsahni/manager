package test.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.activity.BaseAppCompatActivity;

import java.util.Arrays;

/**
 * Created by clickapps on 31/8/17.
 */

public class MainActivity extends BaseAppCompatActivity {


    @Override
    protected void initUI() {
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
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
            startActivity(new Intent(this, SecondActivity.class));
        } else {
            Log.i(getLocalClassName(), "No clickHandled");
        }
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {
        Log.i(getLocalClassName(), "isConnectivity = " + isConnectivity);
    }
}
