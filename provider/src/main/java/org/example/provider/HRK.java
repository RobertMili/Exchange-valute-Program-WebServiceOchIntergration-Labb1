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

@CurrencyAnnotation("HRK")
public class HRK implements CurrencyConverter {


    @Override
    public double getCurrency(double amount) {
        try {
            String url_str = "https://api.exchangerate.host/latest";

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();


            double rate = jsonobj.getAsJsonObject("rates").get("HRK").getAsDouble();


            System.out.println(rate);

            double convertedAmount = amount * rate;
            return convertedAmount;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
