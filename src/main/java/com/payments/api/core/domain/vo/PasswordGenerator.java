package com.payments.api.core.domain.vo;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final int KEY_SIZE = 10; // tamanho mínimo da senha
    private static final SecureRandom random = new SecureRandom();

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+-=[]{};':\"\\|,.<>/?";
    private static final String ALL = UPPER + DIGITS + SPECIAL;

    public static String generate() {

        StringBuilder sb = new StringBuilder();

        // garantir os requisitos mínimos
        sb.append(randomChar(UPPER));     // pelo menos 1 maiúscula
        sb.append(randomChar(DIGITS));    // pelo menos 1 dígito
        sb.append(randomChar(SPECIAL));   // pelo menos 1 especial

        // completar até atingir o tamanho mínimo
        while (sb.length() < KEY_SIZE) {
            sb.append(randomChar(ALL));
        }

        // embaralhar para evitar padrão previsível
        return shuffle(sb.toString());
    }

    private static char randomChar(String chars) {
        return chars.charAt(random.nextInt(chars.length()));
    }

    private static String shuffle(String input) {
        char[] arr = input.toCharArray();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new String(arr);
    }
}

