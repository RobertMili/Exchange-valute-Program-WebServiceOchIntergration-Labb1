package org.example.provider;


import org.example.service.CurrencyConverter;
import org.example.service.annotation.CurrencyAnnotation;

import java.net.*;
import java.io.*;
import com.google.gson.JsonObject;


@CurrencyAnnotation("SEK")
public class SwedishKrona implements CurrencyConverter {
    private final String apiUrl;

    public SwedishKrona() {
        this.apiUrl = "https://api.exchangerate.host/latest";
    }

    @Override
    public double getCurrency(double amount) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonObject json = com.google.gson.JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();

            double rate = json.getAsJsonObject("rates").get("SEK").getAsDouble();

            return amount * rate;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

