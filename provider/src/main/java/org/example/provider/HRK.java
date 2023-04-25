package org.example.provider;

import org.example.service.CurrencyConvert;

public class HRK implements CurrencyConvert {

    public HRK() {
        System.out.println("Kroatiska kuna");
    }


    @Override
    public double getCurrencyConvert(double amount) {
        return 0 * 1.5;
    }
}
