package org.example.provider;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.service.CurrencyConverter;
import org.example.service.annotation.CurrencyAnnotation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@CurrencyAnnotation("Dollar")
public class Dollar implements CurrencyConverter {
    private String apiUrl;

    public Dollar() {
        this.apiUrl = "https://api.exchangerate.host/latest";
    }

    public Dollar(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public double getCurrency(double amount) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonObject json = com.google.gson.JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();

            double rate = json.getAsJsonObject("rates").get("USD").getAsDouble();


            System.out.println(rate);

            return amount * rate;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
