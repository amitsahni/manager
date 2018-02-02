package test.activity;

import com.common.LanguageContextWrapper;
import com.common.application.BaseApplication;

/**
 * Created by clickapps on 2/2/18.
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LanguageContextWrapper.wrap(this, "ar");
    }
}
