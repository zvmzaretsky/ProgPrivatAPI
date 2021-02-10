package com.zvm.privatapi.api;

import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class APIManager {

    private static final String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";

    public Rates loadRatesForDate(LocalDate date) throws IOException {

        String dt = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL + dt)
                .build();

        ResponseBody body = client.newCall(request).execute().body();

        if (body != null) {
            return new Gson().fromJson(body.string(), Rates.class);
        }

        return null;
    }
}
