package com.payments.api.core.vo;

import com.payments.api.core.entities.identity.Document;

import static java.util.Objects.requireNonNull;

public class CNPJ implements Document {

    private final String number;

    private CNPJ(final String number) {

        requireNonNull(number, "Document number can't be null");

        if (!isValid(number)) {
            throw new IllegalArgumentException("CNPJ number is not valid");
        }

        this.number = number;
    }

    public static CNPJ of(final String number) {
        return new CNPJ(number);
    }

    @Override
    public boolean isValid(final String number) {

        if (number == null) {
            return false;
        }

        String cnpj = number.replaceAll("[^0-9]", "");


        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int sum = 0;
            int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 12; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weights1[i];
            }
            int firstDigit = sum % 11;
            firstDigit = (firstDigit < 2) ? 0 : 11 - firstDigit;

            // Verifica o primeiro dígito
            if (firstDigit != Character.getNumericValue(cnpj.charAt(12))) {
                return false;
            }

            // Calcula o segundo dígito verificador
            sum = 0;
            int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 13; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weights2[i];
            }
            int secondDigit = sum % 11;
            secondDigit = (secondDigit < 2) ? 0 : 11 - secondDigit;

            // Verifica o segundo dígito
            return secondDigit == Character.getNumericValue(cnpj.charAt(13));

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getNumber() {
        return this.number;
    }
}
