package org.example.provider;


import org.example.service.CurrencyConverter;
import org.example.service.annotation.CurrencyAnnotation;


@CurrencyAnnotation("Dollar")
public class Dollar implements CurrencyConverter {


    @Override
    public double getCurrency(double amount) {
        return amount * 0.097;
    }
}
