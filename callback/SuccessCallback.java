package com.ayazalam.paytmsdk.paytm_integration.callback;

public interface SuccessCallback<T> {
    void onSuccess(T object);
    void onFailure(String message);
}
