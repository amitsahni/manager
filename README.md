# activity-frag-util

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