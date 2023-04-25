package org.example.provider;

import org.example.service.CurrencyConvert;

public class Euro implements CurrencyConvert {

    public Euro() {
        System.out.println("Euro");
    }

    @Override
    public double getCurrencyConvert(double amount) {
        return amount * 0.088;
    }
}
