package test.activity;

import android.util.Log;
import android.view.View;
import com.activity.BaseAppCompatActivity;
import com.common.LanguageContextWrapper;

import java.util.Locale;

/**
 * Created by clickapps on 31/8/17.
 */

public class SecondActivity extends BaseAppCompatActivity {

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_second);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        Log.i(getLocalClassName(), "Default Language =" + Locale.getDefault().getLanguage());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            LanguageContextWrapper.wrap(this, "ar", true);
        }
        if (id == R.id.button1) {
            LanguageContextWrapper.wrap(this, "en", true);
        } else {
            Log.i(getLocalClassName(), "No clickHandled");
        }
    }

    @Override
    public void onConnectivityChange(boolean isConnectivity) {
        Log.i(getLocalClassName(), "isConnectivity = " + isConnectivity);
    }
}
