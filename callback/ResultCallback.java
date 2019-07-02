package com.ayazalam.paytmsdk.paytm_integration.callback;

public interface ResultCallback {
    void onSuccessfullTransaction();
    void onUnSuccessfullTransaction();
    void onError(String error);
}
