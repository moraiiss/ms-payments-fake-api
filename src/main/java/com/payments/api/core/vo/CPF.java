package com.payments.api.core.vo;

import static java.util.Objects.requireNonNull;

public class CPF {

    private final String number;

    private CPF(final String number) {

        requireNonNull(number, "Document number can't be null");

        String cpf = number.replaceAll("[^0-9]", "");

        if (!isValid(cpf)) {
            throw new IllegalArgumentException("CPF number is not valid");
        }

        this.number = cpf;
    }

    public static CPF of(final String number) {
        return new CPF(number);
    }

    public boolean isValid(final String number) {

        if (number == null) {
            return false;
        }


        if (number.length() != 11) {
            return false;
        }

        if (number.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(number.charAt(i)) * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) {
                firstDigit = 0;
            }

            // Verifica o primeiro dígito
            if (firstDigit != Character.getNumericValue(number.charAt(9))) {
                return false;
            }

            // Calcula o segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(number.charAt(i)) * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) {
                secondDigit = 0;
            }

            // Verifica o segundo dígito
            return secondDigit == Character.getNumericValue(number.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }

    public String getNumber() {
        return this.number;
    }


}
