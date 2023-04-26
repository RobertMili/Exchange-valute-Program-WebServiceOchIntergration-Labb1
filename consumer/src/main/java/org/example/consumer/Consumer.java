package org.example.consumer;


import org.example.service.CurrencyConverter;
import org.example.service.annotation.CurrencyAnnotation;

import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Consumer {

    public static void main(String[] args) {
        ConversionOption option;


        while (true) {
            mainMenu();
            int choice = getIntInput("Choice menu: ");

            option = ConversionOption.getByChoice(choice);

            if (option == null) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }

            switch (option) {
                case EXIT:
                    System.out.println("Thank you for using the program. Program is exiting now!");
                    System.exit(0);
                    break;

                case CONVERT_TO_DOLLAR:
                    double dollarAmount = getConvertAmount();
                    convertToCurrency("Dollar", dollarAmount);
                    break;

                case CONVERT_TO_EUR:
                    double euroAmount = getConvertAmount();
                    convertToCurrency("EUR", euroAmount);
                    break;

                case CONVERT_TO_HRK:
                    double hrkAmount = getConvertAmount();
                    convertToCurrency("HRK", hrkAmount);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private static void mainMenu() {
        System.out.println("""
            Main menu
            ================
            1. Convert SEK to Dollar
            2. Convert SEK to Euro
            3. Convert SEK to HRK
            0. Exit program.
            """);
    }

    private static double getConvertAmount() {
        return getDoubleInput("Enter amount to convert: ");
    }

    private static void convertToCurrency(String currencyCode, double amount) {
        List<CurrencyConverter> converters = getCurrencyConverter(currencyCode);

        if (converters.isEmpty()) {
            System.out.println("No currency converter found for " + currencyCode);
        } else {
            for (CurrencyConverter converter : converters) {
                double convertedAmount = converter.getCurrency(amount);
                System.out.printf("\n%.2f SEK is equal to %.2f %s%n \n", amount, convertedAmount, currencyCode);
            }
        }
    }

    private static List<CurrencyConverter> getCurrencyConverter(String currencyCode) {
        return ServiceLoader.load(CurrencyConverter.class)
                .stream()
                .filter(provider -> provider.type().isAnnotationPresent(CurrencyAnnotation.class))
                .filter(provider -> provider.type().getAnnotation(CurrencyAnnotation.class).value().equals(currencyCode))
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static final Scanner sc = new Scanner(System.in);
}
