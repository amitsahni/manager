package test.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.common.LanguageContextWrapper;
import com.common.application.BaseApplication;

import org.jetbrains.annotations.NotNull;

/**
 * Created by clickapps on 2/2/18.
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setPackageName(getPackageName());
        LanguageContextWrapper.wrap(this, "ar");


    }

    public void gallery(Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), 100);
    }

}
