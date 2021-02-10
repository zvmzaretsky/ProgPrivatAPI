package com.zvm.privatapi.api;

import java.util.ArrayList;

public class Rates {

    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private ArrayList<Currency> exchangeRate;

    public String getDate() {
        return date;
    }

    public String getBank() {
        return bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public ArrayList<Currency> getExchangeRate() {
        return exchangeRate;
    }
}
