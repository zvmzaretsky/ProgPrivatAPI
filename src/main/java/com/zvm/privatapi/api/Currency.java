package com.zvm.privatapi.api;

public class Currency {

    private String baseCurrency;
    private String currency;
    private float saleRateNB;
    private float purchaseRateNB;
    private float saleRate;
    private float purchaseRate;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public float getSaleRateNB() {
        return saleRateNB;
    }

    public float getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public float getSaleRate() {
        return saleRate;
    }

    public float getPurchaseRate() {
        return purchaseRate;
    }
}
