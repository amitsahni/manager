
ActivityFragUtil ![](https://jitpack.io/v/amitclickapps/activity-frag-util.svg?style=flat-square)
-----------------------

#### Start Activity

```
ActivityManager.with(SampleActivity.this, ActivityParam.ActivityType.START)
                        .klass(SampleActivity.class)
                        .activityCompactOption(activityCompatOption)
                        .bundle(bundle)
                        .flag(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .build();
```
#### Start Activity for Result
```
ActivityManager.with(SampleActivity.this, ActivityParam.ActivityType.START_RESULT)
                        .klass(SampleActivity.class)
                        .activityCompactOption(activityCompatOption)
                        .bundle(bundle)
                        .requestCode(100)
                        .build();
```
#### Finish 
```
 ActivityManager.with(SampleActivity.this, ActivityParam.ActivityType.FINISH)
                        .build();
```

#### Fragment 
```
FragmentManager.with(this,R.id.replaceId)
                        .animation(enter,exit,popEnter,popExit)
                        .backStack(true)
                        .enableAnimation(true)
                        .fragment(fragToOpen)
                        .tag(FragmentTag)
                        .type(FragParam.FragType.REPLACE)
                        .build();
```
#### Extend `BaseAppCompatActivity`
##### If Connectivity Changes automatic notified under `OnConnectivityChanges`

```
public class SampleActivity extends BaseAppCompatActivity{

@Override
    protected void initUI() {
        setContentView(R.layout.activity_sample);
    }
 
 @Override
    public void onConnectivityChange(boolean isConnectivity) {
        super.onConnectivityChange(isConnectivity);
        Log.i(getLocalClassName(), "onConnectivityChange = " + isConnectivity);
    }
  }
```

#### If want Result `onActivityResult` from camera, Gallery have to use this in `OnResume()`
```
 enableOnActivityResultFragment(true) 
```
#### Update Locale
#### 1. Context
#### 2. LanguageCode
#### 3. Enable/Disable BroadCast for reflecting in whole application
```
 LanguageContextWrapper.wrap(this, "en", true);
```

Download
--------
Add the JitPack repository to your root build.gradle:


![](https://jitpack.io/v/amitclickapps/activity-frag-util.svg?style=flat-square)

```groovy
	allprojects {
		repositories {
			maven { url "https://jitpack.io" }
		}
	}
```
Add the Gradle dependency:
```groovy
	dependencies {
		compile 'com.github.amitclickapps:image-util:latest'
	}
```