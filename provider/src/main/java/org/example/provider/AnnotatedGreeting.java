package org.example.provider;

import org.example.service.annotation.CurrencyAnnotate;

@CurrencyAnnotate("sv")
public class AnnotatedGreeting {

    String hello(){
        return "Hej";
    }
}
