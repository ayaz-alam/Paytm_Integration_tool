package com.ayazalam.paytmsdk.paytm_integration.callback;

public interface ChecksumCallback {
    void onSuccess(String checksum);
    void onFailure(String message);
}
