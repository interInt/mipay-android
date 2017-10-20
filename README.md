# MIPAY Android Sdk
### Quick Setup
- Add the JitPack repository to your build file 
Add it in your `build.gradle` at the end of repositories:
```
repositories {
    // ...
    maven { url "https://jitpack.io" }
}
```
       
- Add **mipay** dependency
```

dependencies {
        //...
    compile 'com.github.interintel-opensource:mipay-android:v1.0.0'
}
```

### Usage

#### Checkout

```java

    // inside an activity
    // this will open the mipay app if installed else opens a webpage for the payment options
    String reference  = "1-INTINT-CVI35";
    Mipay.checkout(this,reference);

    // ...
    
```

To get the result, you need to handle the intent
```java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Mipay.REQUEST_CODE_MIPAY_PAYMENT:
                if (resultCode == Activity.RESULT_OK) {
                    String result = data.getStringExtra("message");
                    Log.i(TAG,result);
                    try {
                        JSONObject payload = new JSONObject(result);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }

                break;

            default:
                break;
        }
    }

```
