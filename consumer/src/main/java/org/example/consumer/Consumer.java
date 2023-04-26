package org.example.consumer;


import org.example.service.CurrencyConverter;
import org.example.service.annotation.CurrencyAnnotation;

import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Consumer {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ConversionOption option;

        while (true) {
            mainMenu();
            String choiceStr = sc.nextLine();

            if (choiceStr.isEmpty()) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }
            int choice;

            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }

            option = ConversionOption.getByChoice(choice);
            if (option == null) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }


            switch (option) {
                case EXIT -> {
                    System.out.println("Thank you for use program. Program is exits now!");
                    System.exit(0);

                }
                case CONVERT_TO_DOLLAR -> {
                    double convertAmount = getConvertAmount();
                    sc.nextLine();
                    convertToDollar(Double.parseDouble(String.valueOf(convertAmount)));

                }
                case CONVERT_TO_EUR -> {
                    double convertAmount = getConvertAmount();
                    sc.nextLine();
                    convertToEur(Double.parseDouble(String.valueOf(convertAmount)));

                }
                case CONVERT_TO_HRK -> {
                    double convertAmount = getConvertAmount();
                    sc.nextLine();
                    convertToHRK(Double.parseDouble(String.valueOf(convertAmount)));

                }
            }

        }
    }

    private static double getConvertAmount() {
        System.out.println("Write how much amount wanna to convert it:");
        return sc.nextDouble();
    }

    private static void mainMenu() {
        final String menuText = """
                    Main menu
                 ================
                 1. Convert Sek to Dollar
                 2. Convert Sek to Euro
                 3. Convert Sek to HRK
                 0. Exit program.
                 
                Choice menu:""";

        System.out.println(menuText);
    }


    private static List<CurrencyConverter> getCurrencyConverter(String currency) {
        ServiceLoader<CurrencyConverter> currencyConverters = ServiceLoader.load(CurrencyConverter.class);
        return currencyConverters.stream()
                .filter(provider -> provider.type().isAnnotationPresent(CurrencyAnnotation.class))
                .filter(provider -> provider.type().getAnnotation(CurrencyAnnotation.class).value().equals(currency))
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    private static void convertToDollar(double amount) {
        for (var converters : getCurrencyConverter("Dollar")) {
            System.out.println(amount + " SEK is equal to: " + converters.getCurrency(amount) + " Dollar");
        }
    }

    private static void convertToEur(double amount) {
        for (var converters : getCurrencyConverter("EUR")) {
            System.out.println(amount + " SEK is equal to: " + converters.getCurrency(amount) + " EUR");
        }
    }

    private static void convertToHRK(double amount) {
        for (var converters : getCurrencyConverter("HRK")) {
            System.out.println(amount + " SEK is equal to: " + converters.getCurrency(amount) + " HRK");
        }
    }
}
