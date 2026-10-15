package com.example.learnhub.utils;

import java.util.Scanner;

public final class InputValidator {
    private static final int MAXIMUM_ATTEMPTS = 5;
    private InputValidator() {
    }

    public static int readInt(Scanner scanner, String prompt) {
        int result = 0;
        boolean valid = false;
        int attempts = 0;
        while (!valid && attempts < MAXIMUM_ATTEMPTS) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                result = Integer.parseInt(line);
                valid = true;
            }

            catch (NumberFormatException exception) {
                attempts++;
                System.out.println("Некорректный ввод. Введите целое число. Попытка "
                        + attempts + " из " + MAXIMUM_ATTEMPTS + ".");
            }
        }

        if (!valid) {
            System.out.println("Слишком много некорректных попыток. Использую 0.");
        }
        return result;
    }

    public static int readInt(Scanner scanner, String prompt, int defaultValue) {
        System.out.print(prompt);
        String line = scanner.nextLine().trim();
        int result = defaultValue;
        if (!line.isEmpty()) {
            try {
                result = Integer.parseInt(line);
            }

            catch (NumberFormatException exception) {
                System.out.println("Некорректный ввод. Использую значение по умолчанию "
                        + defaultValue + ".");
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
            }

            else {
                try {
                    int value = Integer.parseInt(line);
                    if (value > 0) {
                        result = value;
                        valid = true;
                    }

                    else {
                        attempts++;
                        System.out.println("Число должно быть положительным. Попытка "
                                + attempts + " из " + MAXIMUM_ATTEMPTS + ".");
                    }
                }

                catch (NumberFormatException exception) {
                    attempts++;
                    System.out.println("Некорректный ввод. Введите целое число. Попытка "
                            + attempts + " из " + MAXIMUM_ATTEMPTS + ".");
                }
            }
        }

        if (!valid) {
            System.out.println("Слишком много некорректных попыток. Использую "
                    + defaultValue + ".");
            result = defaultValue;
        }
        return result;
    }
}