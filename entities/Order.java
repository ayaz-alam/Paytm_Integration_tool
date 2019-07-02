package com.ayazalam.paytmsdk.paytm_integration.entities;

public class Order {

    private String orderID, customerID, mobileNo, emailId, transactionAmount, checksumHash;

    public Order(String orderId, String customerId, String mobileNumber, String email, String txn_amount) {
        orderID = orderId;
        customerID = customerId;
        mobileNo = mobileNumber;
        emailId = email;
        transactionAmount = txn_amount;
    }

    public void setCheckSumHash(String checkSumHash) {
        this.checksumHash = checkSumHash;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public String getChecksumHash() {
        return checksumHash;
    }
}
