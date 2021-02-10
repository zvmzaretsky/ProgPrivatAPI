package com.zvm.privatapi;

import com.zvm.privatapi.api.APIManager;
import com.zvm.privatapi.api.Currency;
import com.zvm.privatapi.api.Rates;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        APIManager apiManager = new APIManager();
        DatabaseManager dbManager = new DatabaseManager();

        dbManager.restartDB();
        LocalDate sample = LocalDate.now().minusDays(5);

        for (int i = 0; i < 5; i++) {

            LocalDate date = sample.minusDays(i);
            Rates single = apiManager.loadRatesForDate(date);

            dbManager.insertData(single.getDate(), single.getExchangeRate()
                    .stream()
                    .filter(c -> c.getCurrency().equals("USD"))
                    .findFirst()
                    .map(Currency::getPurchaseRate)
                    .orElse(0F));
        }

        System.out.println(dbManager.getMax());
        System.out.println(dbManager.getAvarage());
    }
}
