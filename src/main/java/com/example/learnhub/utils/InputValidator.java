package com.example.learnhub.utils;

import java.util.Scanner;

public final class InputValidator {
    private static final int MAXIMUM_ATTEMPTS = 5;

    private InputValidator() {
    }

    public static int readInt(Scanner scanner, String prompt) {
        int attempts = 0;
        while (attempts < MAXIMUM_ATTEMPTS) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException exception) {
                attempts++;
                System.out.println(String.format(
                        "Некорректный ввод. Введите целое число. Попытка %d из %d.",
                        attempts, MAXIMUM_ATTEMPTS));
            }
        }
        throw new IllegalArgumentException("Слишком много некорректных попыток ввода.");
    }

    public static int readInt(Scanner scanner, String prompt, int defaultValue) {
        System.out.print(prompt);
        String line = scanner.nextLine().trim();
        int result = defaultValue;
        if (!line.isEmpty()) {
            try {
                result = Integer.parseInt(line);
            } catch (NumberFormatException exception) {
                System.out.println(String.format(
                        "Некорректный ввод. Использую значение по умолчанию %d.",
                        defaultValue));
            }
        }
        return result;
    }

    public static int readPositiveInt(Scanner scanner, String prompt, int defaultValue) {
        int result = defaultValue;
        boolean valid = false;
        int attempts = 0;
        while (!valid && attempts < MAXIMUM_ATTEMPTS) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                result = defaultValue;
                valid = true;
            } else {
                try {
                    int value = Integer.parseInt(line);
                    if (value > 0) {
                        result = value;
                        valid = true;
                    } else {
                        attempts++;
                        System.out.println(String.format(
                                "Число должно быть положительным. Попытка %d из %d.",
                                attempts, MAXIMUM_ATTEMPTS));
                    }
                } catch (NumberFormatException exception) {
                    attempts++;
                    System.out.println(String.format(
                            "Некорректный ввод. Введите целое число. Попытка %d из %d.",
                            attempts, MAXIMUM_ATTEMPTS));
                }
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Слишком много некорректных попыток ввода.");
        }
        return result;
    }
}
