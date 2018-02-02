
# Version - 1.0.1-alpha
----
##### Really Important
##### BaseApplication need to extent or directly put in your app manifest

### Start Activity
```
ActivityManager.with(getApplicationContext())
                    .startActivity(SecondActivity.class)
                    .build();
                    
                    
```
### Start Activity Finish
```
ActivityManager.with(getApplicationContext())
                    .startActivityFinish(SecondActivity.class)
                    .build();
                    
```
### Start Activity For Result
```                    
ActivityManager.with(getApplicationContext())
                    .startActivityForResult(SecondActivity.class,requestCode)
                    .build();
```
### Start Activity For Result Finish
```                                        
ActivityManager.with(getApplicationContext())
                    .startActivityForResultFinish(SecondActivity.class,requestCode)
                    .build();                                        
```   
### Finish
```                                        
ActivityManager.with(getApplicationContext())
                    .finish()
                    .build();                                        
```                   
                    
Optional 
``` 
   .activityCompactOption(activityCompactOption)
   .sharedElements(new View[]{getBtn})
   .animation(enter,exit)
   .bundle(bundle)
```
`compile 'com.github.amitsahni.manager:activity:1.0.1-alpha'`


#### Fragment Replace
```
FragmentManager.with(this)
               .replace(R.id.replace,fragment)
               .build();
```
#### Fragment pop
```
FragmentManager.with(this)
               .pop(tag)
               .build();
FragmentManager.with(this)
               .pop(id)
               .build();               
```
#### Fragment restart
```
FragmentManager.with(this)
               .restart(fragment)
               .build();
```

#### Fragment Utils
```
FragmentManager.with(this)
               .utils()
               .getFragment(id);
               
FragmentManager.with(this)
               .utils()
               .getFragment(tag); 
                     
FragmentManager.with(this)
               .utils()
               .getStackList();   
               
FragmentManager.with(this)
               .utils()
               .getTopFragmentById(); 
FragmentManager.with(this)
               .utils()
               .getTopFragmentByTag();                                                  
```
`compile 'com.github.amitsahni.manager:fragment:1.0.1-alpha'`

#### Extend `BaseAppCompatActivity`
##### If Connectivity Changes automatic notified under `OnConnectivityChanges` but for this need to add `BaseApplication` in your manifest

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

```groovy
	allprojects {
		repositories {
			maven { url "https://jitpack.io" }
		}
	}
```
