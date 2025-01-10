package com.jorgeoc;

import java.util.Scanner;

public class CurrencyConverter {

    private static final String[] CURRENCY_CODES = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    public static void main(String[] args) {
        ExchangeRateAPI api = new ExchangeRateAPI();
        Menu menu = new Menu(CURRENCY_CODES, api);
        menu.start();
    }
}
