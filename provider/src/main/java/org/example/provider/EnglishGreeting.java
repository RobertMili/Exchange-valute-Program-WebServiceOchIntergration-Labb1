package org.example.provider;

import org.example.service.Greeting;

public class EnglishGreeting implements Greeting {

    public EnglishGreeting() {
        System.out.println("English created");
    }

    @Override
    public String sayHello() {
        return "Hallo";
    }
}
