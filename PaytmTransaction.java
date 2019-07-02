package com.ayazalam.paytmsdk.paytm_integration;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.ayazalam.paytmsdk.paytm_integration.entities.Order;
import com.ayazalam.paytmsdk.paytm_integration.callback.ChecksumCallback;
import com.ayazalam.paytmsdk.paytm_integration.network.CheckSumHelper;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;

import static com.ayazalam.paytmsdk.paytm_integration.Constants.VERIFY_URL;

public class PaytmTransaction {
    private PaytmPaymentTransactionCallback callback;
    private Context context;
    private Order order;
    private ProgressDialog dialog;

    public PaytmTransaction(Context context, PaytmPaymentTransactionCallback callback){
        this.context =context;
        this.callback =callback;
        dialog = new ProgressDialog(context);
    }


    public void initTransacation(@NonNull final String orderID, @NonNull String customerId, @NonNull String transactionAmount, @NonNull String phoneNumber, @NonNull String emailAddress){
        dialog.show();
        order = new Order(orderID,customerId,phoneNumber,emailAddress,transactionAmount);
        CheckSumHelper.getChecksum(context, order, new ChecksumCallback() {
            @Override
            public void onSuccess(String checksum) {
                order.setCheckSumHash(checksum);
                proceedWithTransaction();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context,"Error "+message,Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });


    }

    private void proceedWithTransaction(){

        PaytmPGService service ;
        if(PaytmConfig.IS_STAGING)
            service= PaytmPGService.getStagingService();
        else
            service= PaytmPGService.getProductionService();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        //these are mandatory parameters
        paramMap.put(Constants.MID, PaytmConfig.MERCHANT_ID); //MID provided by paytm
        paramMap.put(Constants.ORDER_ID, order.getOrderID());
        paramMap.put(Constants.CUST_ID, order.getCustomerID());
        paramMap.put(Constants.CHANNEL_ID, "WAP");
        paramMap.put(Constants.TXN_AMOUNT, order.getTransactionAmount());
        paramMap.put(Constants.WEBSITE,PaytmConfig.WEBSITE);
        paramMap.put(Constants.CALLBACK_URL , VERIFY_URL);
        paramMap.put(Constants.CHECKSUMHASH,order.getChecksumHash());
        paramMap.put(Constants.INDUSTRY_TYPE_ID,PaytmConfig.INDUSTRY_TYPE);
        PaytmOrder paytmOrder = new PaytmOrder(paramMap);
        service.initialize(paytmOrder,null);
        service.startPaymentTransaction(context, true, true, callback);
        dialog.dismiss();
    }

}
