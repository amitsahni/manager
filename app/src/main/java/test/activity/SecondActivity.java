package test.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.activityutil.activity.activity.BaseAppCompatActivity;
import com.activityutil.activity.activity.LanguageContextWrapper;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by clickapps on 31/8/17.
 */

public class SecondActivity extends BaseAppCompatActivity {
    @BindView(R.id.button)
    Button getBtn;

    @BindView(R.id.button1)
    Button chnageLanguage;


    @Override
    protected void initUI() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getBtn.setOnClickListener(this);
        chnageLanguage.setOnClickListener(this);
        Log.i(getLocalClassName(),"Default Language =" + Locale.getDefault().getLanguage());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
//            ActivityManager.with(this, ActivityParam.ActivityType.FINISH)
//                    .build();
            LanguageContextWrapper.wrap(this, "ar", true);
        } if (id == R.id.button1) {
//            ActivityManager.with(this, ActivityParam.ActivityType.FINISH)
//                    .build();
            LanguageContextWrapper.wrap(this, "en", true);
        } else {
            Log.i(getLocalClassName(), "No clickHandled");
        }
    }
}
