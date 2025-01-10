package com.jorgeoc;

import com.google.gson.JsonObject;

import java.util.Scanner;

public class Menu {

    private final String[] currencyCodes;
    private final ExchangeRateAPI api;

    public Menu(String[] currencyCodes, ExchangeRateAPI api) {
        this.currencyCodes = currencyCodes;
        this.api = api;
    }

    public void start() {
        try {
            JsonObject jsonObject = api.fetchExchangeRates();
            JsonObject rates = jsonObject.get("rates").getAsJsonObject();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Convertir moneda");
                System.out.println("2. Salir");
                int option = scanner.nextInt();

                if (option == 2) {
                    break;
                } else if (option == 1) {
                    System.out.println("Ingrese el monto a convertir:");
                    double amount = scanner.nextDouble();
                    System.out.println("Seleccione la moneda de origen:");
                    String fromCurrency = selectCurrency(scanner);
                    System.out.println("Seleccione la moneda de destino:");
                    String toCurrency = selectCurrency(scanner);

                    double fromRate = rates.get(fromCurrency).getAsDouble();
                    double toRate = rates.get(toCurrency).getAsDouble();
                    double convertedAmount = (amount / fromRate) * toRate;

                    System.out.println(amount + " " + fromCurrency + " es igual a " + convertedAmount + " " + toCurrency);
                }
            }
            scanner.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String selectCurrency(Scanner scanner) {
        System.out.println("Monedas disponibles:");
        for (int i = 0; i < currencyCodes.length; i++) {
            System.out.println((i + 1) + ". " + currencyCodes[i]);
        }
        int choice = scanner.nextInt();
        return currencyCodes[choice - 1];
    }
}
