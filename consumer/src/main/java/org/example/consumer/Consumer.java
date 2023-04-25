package org.example.consumer;


import org.example.service.Greeting;

import java.util.Map;
import java.util.ServiceLoader;

public class Consumer {
    public static void main(String[] args) {


        //Leta efter klasser i paketet org.example.provider

        //KOlla om klassen har annotation @Language

        //Anropa klassens metod/medoter? och skriva ut returnvärder



        //Find all implementations of Greeting..
//        ServiceLoader<Greeting> greetings = ServiceLoader.load(Greeting.class);
//
//        for (var greeting : greetings) {
//            System.out.println(greeting.sayHello());
//        }




    }
}
//        var greetings = serviceLoader.stream()
//                .filter(provider -> provider.type().getSimpleName().startsWith("Swedish"))
//                .map(ServiceLoader.Provider::get)
//                .toList();
//
//        for ( var greeting : greetings) {
//            if (greeting.getClass().getSimpleName().startsWith("Swedish"))
//                System.out.println(greeting.greet());
//        }