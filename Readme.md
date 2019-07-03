# Paytm Integration tool

This is a simple library to include paytm into your application


### Step 1:  Include paytm sdk dependency in **app level gradle**
```javascript
dependencies {
	implementation('com.paytm:pgplussdk:1.3.3') {
		transitive = true;
	}
}
```

### Step 2: Add Volley dependency in **app level gradle**
```javascript
dependencies {
	implementation 'com.android.volley:volley:1.1.1'
}
```

### Step 3: Add paytm activity in manifest like below
```xml
<activity android:name="com.paytm.pgsdk.PaytmPGActivity"
   android:screenOrientation="portrait" android:configChanges="keyboardHidden|orientation|keyboard"/>
```

### Step 4: Download and paste this module in your package

Download from here : [Download Zip](https://github.com/Ayaz922/Paytm_Integration_tool/archive/master.zip)


### Step 5: Goto PaytmConfig java file and change credentials accordingly.
```java
public class PaytmConfig {

    //A boolean to fix the configuration of the paytm sdk, true for staging.
    public static final boolean IS_STAGING =true;
    //Merchant Id as provided by Paytm
    public static final String MERCHANT_ID="MerchantIDXXXXX";
    //Website : for staging use "WEBSTAGING" otherwise use the website provided after activation of program
    public static final String WEBSITE ="WEBSTAGING";
    //Industry type, use "Retail" for staging
    public static final String INDUSTRY_TYPE ="Retail";
    //Checksum generation URL, this is a url of remote server that executes a php config file to generate
    // a checksum according to the api key and given fields.
    public static final String CHECKSUM_GEN_URL ="https://..../generatepaytmchecksum";

}
```
To get CHECKSUM_GEN_URL, you'll have to upload library provided by paytm itself,
link to download is : [CheckSum Files](https://github.com/Paytm-Payments/Paytm_App_Checksum_Kit_PHP/archive/master.zip)
after downloading, goto Paytm_App_Checksum_Kit_PHP/lib/config_paytm.php and change the value of PAYTM_MERCHANT_KEY constant with details received from Paytm.

```php
<?php
//Change the value here
define('PAYTM_MERCHANT_KEY', 'XXXXXXXXXXXXXXX');
?>
```

after replacing the merchant key, upload this folder to your server and get the url to post request and change it in PaytmConfig.java file in package
(You can use any hosting if you don't have your own server, 000webhost.com would do the work)

### Step 6: Implement PaytmPaymentTransactionCallback interface.
In your activity where you are taking transaction data, implement the callback PaytmPaymentTransactionCallback
```java

public class MainActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    @Override
    public void onTransactionResponse(Bundle inResponse) {

    }

    @Override
    public void networkNotAvailable() {

    }

    @Override
    public void clientAuthenticationFailed(String inErrorMessage) {

    }

    @Override
    public void someUIErrorOccurred(String inErrorMessage) {

    }

    @Override
    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

    }

    @Override
    public void onBackPressedCancelTransaction() {

    }

    @Override
    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

    }
}

```

### Step 7: Create a simple request as shown below and you're good to go.
```java
//Make sure to complete step 5, otherwise you'll need to create the callback
PaytmTransaction transaction =new PaytmTransaction(this,this);
transaction.initTransacation(orderId,customerId,transactionAmount,phoneNumber,emailAddress);
```

### MainActivity would look like this.

   ```java

public class MainActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    private static final int SMS_PERMISSIONS = 2212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check for sms permission
        if(checkPermission()) {
            String orderId = "12453";
            String customerId = "21321";
            String transactionAmount = "100";
            String phoneNumber = "90XXXXXXX5";
            String emailAddress = "axxxxxxx@gmail.com";
            PaytmTransaction transaction = new PaytmTransaction(this, this);
            transaction.initTransacation(orderId, customerId, transactionAmount, phoneNumber, emailAddress);
        }



    }

    private void proceed() {

    }

    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, SMS_PERMISSIONS);
                return false;
            }else return true;
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==SMS_PERMISSIONS){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                proceed();
                Toast.makeText(this,"Permission granted",Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show();

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onTransactionResponse(Bundle inResponse) {

    }

    @Override
    public void networkNotAvailable() {

    }

    @Override
    public void clientAuthenticationFailed(String inErrorMessage) {

    }

    @Override
    public void someUIErrorOccurred(String inErrorMessage) {

    }

    @Override
    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

    }

    @Override
    public void onBackPressedCancelTransaction() {

    }

    @Override
    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

    }
}

   ```
