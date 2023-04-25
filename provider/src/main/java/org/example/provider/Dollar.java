package org.example.provider;


import org.example.service.CurrencyConvert;

public class Dollar implements CurrencyConvert {

    public Dollar() {
        System.out.println("USE dollar");
    }

    @Override
    public double getCurrencyConvert(double amount) {
        return amount * 10.32;
    }
}
