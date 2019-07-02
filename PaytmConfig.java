package com.ayazalam.paytmsdk.paytm_integration;

/**
 * Created by Ayaz Alam on 7/2/19
 */


//This class holds all the change log, which needs to be changed as per requirement
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
