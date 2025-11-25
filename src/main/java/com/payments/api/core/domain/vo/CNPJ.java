package com.payments.api.core.domain.vo;

import com.payments.api.core.domain.exceptions.InvalidDocumentException;

import static java.util.Objects.requireNonNull;

public record CNPJ(String number) {

    private static final int CNPJ_LENGTH = 14;
    private static final int CNPJ_REPEATED_PATTERN_LENGTH = 13;
    private static final int FIRST_DIGIT_POSITION = 12;
    private static final int SECOND_DIGIT_POSITION = 13;
    private static final int MODULO_DIVISOR = 11;
    private static final int MINIMUM_DIGIT_VALUE = 2;
    private static final int DEFAULT_DIGIT_VALUE = 0;
    private static final int[] FIRST_DIGIT_WEIGHTS = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] SECOND_DIGIT_WEIGHTS = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public CNPJ {

        requireNonNull(number, "Document number can't be null");

        if (!isValid(number)) {
            throw new InvalidDocumentException();
        }
    }

    public static CNPJ of(final String number) {
        return new CNPJ(number);
    }

    public boolean isValid(final String cnpj) {

        String number = cnpj.replaceAll("[^0-9]", "");

        if (number.length() != CNPJ_LENGTH) {
            return false;
        }

        if (number.matches("(\\d)\\1{" + CNPJ_REPEATED_PATTERN_LENGTH + "}")) {
            return false;
        }

        try {
            int sum = 0;
            for (int i = 0; i < FIRST_DIGIT_POSITION; i++) {
                sum += Character.getNumericValue(number.charAt(i)) * FIRST_DIGIT_WEIGHTS[i];
            }
            int firstDigit = sum % MODULO_DIVISOR;
            firstDigit = (firstDigit < MINIMUM_DIGIT_VALUE) ? DEFAULT_DIGIT_VALUE : MODULO_DIVISOR - firstDigit;

            if (firstDigit != Character.getNumericValue(number.charAt(FIRST_DIGIT_POSITION))) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < SECOND_DIGIT_POSITION; i++) {
                sum += Character.getNumericValue(number.charAt(i)) * SECOND_DIGIT_WEIGHTS[i];
            }
            int secondDigit = sum % MODULO_DIVISOR;
            secondDigit = (secondDigit < MINIMUM_DIGIT_VALUE) ? DEFAULT_DIGIT_VALUE : MODULO_DIVISOR - secondDigit;

            return secondDigit == Character.getNumericValue(number.charAt(SECOND_DIGIT_POSITION));

        } catch (Exception e) {
            return false;
        }
    }

    public String getNumber() {
        return this.number;
    }
}
