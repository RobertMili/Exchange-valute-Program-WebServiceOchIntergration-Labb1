package org.example.provider;


import org.example.service.CurrencyConverter;
import org.example.service.annotation.CurrencyAnnotation;

import java.net.*;
import java.io.*;
//import org.json.JSONObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@CurrencyAnnotation("SEK")
public class SvenskKrona implements CurrencyConverter {


    @Override
    public double getCurrency(double amount) {
//        return amount * 0.097;
//    }
//        try {
//            URL url = new URL("https://api.exchangeratesapi.io/latest?base=EUR");
//
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//
//            in.close();
//            JSONObject jsonObj = new JSONObject(response.toString());
//            JSONObject rates = jsonObj.getJSONObject("rates");
//            double rate = rates.getDouble("SEK");


        try {
            String url_str = "https://api.exchangerate.host/latest";

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();


            double rate = jsonobj.getAsJsonObject("rates").get("SEK").getAsDouble();

            double convertedAmount = amount * rate;
            return convertedAmount;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

