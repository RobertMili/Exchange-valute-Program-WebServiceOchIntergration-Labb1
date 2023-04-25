package org.example.provider;


import org.example.service.Greeting;

public class SwedishGreeting implements Greeting {

    public SwedishGreeting() {
        System.out.println("Swedish created");
    }
    @Override
    public String sayHello() {
        return "Hej";
    }
}
