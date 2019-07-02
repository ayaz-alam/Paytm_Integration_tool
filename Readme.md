# Paytm Integration tool

This is a simple library to include paytm into your application


## Step 1:  Include paytm sdk dependency in **app level gradle**
```
dependencies {
	implementation('com.paytm:pgplussdk:1.3.3') {
		transitive = true;
	}
}
```

## Step 2: Add Volley dependency in **app level gradle**
```
dependencies {
	implementation('com.paytm:pgplussdk:1.3.3') {
		transitive = true;
	}
}
```

## Step 3: Add paytm activity in manifest like below
```
<activity android:name="com.paytm.pgsdk.PaytmPGActivity"
   android:screenOrientation="portrait" android:configChanges="keyboardHidden|orientation|keyboard"/>
```

## Step 4: Download and paste this module in your package

## Step 5: Implement PaytmPaymentTransactionCallback interface.
In your activity where you are taking transaction data, implement the callback PaytmPaymentTransactionCallback
```

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

## Step 6: Create a simple request as shown below and you're good to go.
```
//Make sure to complete step 5, otherwise you'll need to create the callback
PaytmTransaction transaction =new PaytmTransaction(this,this);
transaction.initTransacation(orderId,customerId,transactionAmount,phoneNumber,emailAddress);
```

### MainActivity would look like this.

   ```

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



## Developer
* [Ayaz Alam](https://github.com/Ayaz922)      Application Designer and Developer
