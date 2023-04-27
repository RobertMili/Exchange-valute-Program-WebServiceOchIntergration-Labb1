package org.example.consumer;


import org.example.service.CurrencyConverter;
import org.example.service.annotation.CurrencyAnnotation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Consumer {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        ConversionOption option;


        while (true) {
            mainMenu();
            Thread.sleep(1000);
            int choice = getIntInput();

            option = ConversionOption.getByChoice(choice);

            if (option == null) {
                System.out.println("Invalid choice. Please try again");
                continue;
            }

            switch (option) {
                case EXIT -> {
                    System.out.println("Thank you for using the program. Program is exiting now!");
                    sc.close();
                    System.exit(0);
                }
                case CONVERT_TO_SEK -> {
                    double dollarAmount = getConvertAmount();
                    convertToCurrency("SEK", dollarAmount);
                }
                case CONVERT_TO_DOLLAR -> {
                    double sekAmount = getConvertAmount();
                    convertToCurrency("Dollar", sekAmount);
                }
                case CONVERT_TO_HRK -> {
                    double hrkAmount = getConvertAmount();
                    convertToCurrency("HRK", hrkAmount);
                }
                default -> System.out.println("Invalid choice. Please try again");
            }
        }

    }

    private static void mainMenu() {
        System.out.println("""
                Main menu
                ================
                1. Convert EUR to Swedish krona
                2. Convert EUR to Dollar
                3. Convert EUR to HRK
                0. Exit program.
                """);
    }

    private static double getConvertAmount() {
        return getDoubleInput();
    }

    private static void convertToCurrency(String currencyCode, double amount) {
        List<CurrencyConverter> converters = getCurrencyConverter(currencyCode);

        if (converters.isEmpty()) {
            System.out.println("No currency converter found for " + currencyCode);
        } else {
            for (CurrencyConverter converter : converters) {
                double convertedAmount = converter.getCurrency(amount);
                System.out.printf("\n%.2f EUR is equal to %.2f %s%n \n", amount, convertedAmount, currencyCode);
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

    private static int getIntInput() {
        while (true) {
            try {
                System.out.print("Choice menu: ");
                if (sc.hasNext()) {
                    return Integer.parseInt(sc.nextLine());
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");

            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                System.out.print("Enter amount to convert: ");
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }


}
