# MIPAY Android Sdk

This library will open the Mipay app if installed else opens the mipay web-app  enabling making of  
payments and returns the response to the calling activity

Mipay App - https://play.google.com/store/apps/details?id=ke.co.interintel.mipay

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
        compile 'com.github.interintel-opensource:mipay-android:v1.0.2'
        
}
```

### Usage

Payment methods include
```
    Mipay.pmCARD            // CARD
    Mipay.pmM_PESA          // M-PESA
    Mipay.pmAIRTEL_MONEY    // AIRTEL MONEY
    Mipay.pmBANK            // BANK
    Mipay.pmMIPAY           // MIPAY

```

#### Checkout
To obtain a `reference`,   
a service user must have made a prior service request (SALE ORDER) to obtain a reference to use in 
the request.   
A reference can only be used once and would no longer be accessible once a payment is made

```java

    // inside an activity
    String reference  = "1-INTINT-CVI35";
    Mipay.checkout(this,reference);

    // ... or with specifying a payment method 
    Mipay.checkout(this,reference,Mipay.pmM_PESA); // example for only m-pesa 
```


#### Sale
To obtain `product_item_id`, the service user would be issued with the `product_item_id` Identifying the  
product for sale and can be used repeatedly to make  multiple sales
```java

    // inside an activity
    // 
    int productItemID = 99781;
    int quantity = 10;
    Mipay.sale(this, productItemID, quantity);
    
    // ... or with specifying a payment method 
    Mipay.sale(this,productItemID, quantity, Mipay.pmM_PESA); // example for only m-pesa 
    
    // ...

    
```
#### Order
To obtain `cart_items`, the service (`ADD TO CART`) must have been done, and the items on cart obtained, 
```java

    // inside an activity
    int[] cartItems = {8374, 8375, 8378};
    Mipay.order(this, cartItems);
    
    // ... or with specifying a payment method 
    Mipay.order(this, cartItems, Mipay.pmM_PESA); // example for only m-pesa 
        
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
