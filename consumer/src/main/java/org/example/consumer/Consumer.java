package org.example.consumer;


import org.example.service.CurrencyConvert;

import java.lang.reflect.InvocationTargetException;
import java.util.ServiceLoader;

public class Consumer {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {


//        //Leta efter klasser i paketet org.example.provider
//
//        Set<Class> classes = findAllClasses("org.example.provider");
//
//        //Kolla om klassen har annotation @Language
//        for (var c : classes) {
//            var annotation = (Language) c.getAnnotation(Language.class);
//            if (annotation != null) {
//                System.out.println(annotation.value());
//                //Anropa klassens metod/medoter? och skriva ut returnvärder
//                var methods = c.getMethods();
//                for (var m : methods ) {
//                    if (m.getReturnType().equals(String.class) && m.getParameterCount() == 0) {
//                        var s = m.invoke(c);
//                        if (s instanceof  String string)
//                        System.out.println(s);
//                    }
//                }
//            }
//        }


//        Find all implementations of Greeting..
        ServiceLoader<CurrencyConvert> greetings = ServiceLoader.load(CurrencyConvert.class);

        for (var greeting : greetings) {
            System.out.println(greeting.sayHello());
        }


    }

//    private static Set<Class> findAllClasses(String s) {
//        return Set.of();
//    }
}
