
[ ![Download](https://api.bintray.com/packages/amitsahni/Library/base/images/download.svg?version=0.0.1-alpha01) ](https://bintray.com/amitsahni/Library/base/0.0.1-alpha01/link)

----
##### Really Important
##### BaseApplication need to extent or directly put in your app manifest

Use `BaseAppCompatActivity` , `BaseFragment`

#### Update Locale
#### 1. Context
#### 2. LanguageCode
#### 3. Enable/Disable BroadCast for reflecting in whole application
```
 LanguageContextWrapper.wrap(this, "en", true);
```

Download
--------

```groovy
implementation 'com.amitsahni:base:0.0.1-aplha01'
```
Add the JitPack repository to your root build.gradle:

```groovy
	repositories {
        maven {
            url  "https://dl.bintray.com/amitsahni/Library" 
        }
    }
```
