package test.activity;

import android.content.Context;

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

}
