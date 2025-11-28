package com.payments.api.core.domain.vo;

import com.payments.api.core.domain.exceptions.InvalidDocumentException;

import static java.util.Objects.requireNonNull;

public record CPF(String number) implements Document {

    private static final int CPF_LENGTH = 11;
    private static final int CPF_REPEATED_PATTERN_LENGTH = 10;
    private static final int FIRST_DIGIT_CALCULATION_LENGTH = 9;
    private static final int SECOND_DIGIT_CALCULATION_LENGTH = 10;
    private static final int FIRST_DIGIT_POSITION = 9;
    private static final int SECOND_DIGIT_POSITION = 10;
    private static final int FIRST_DIGIT_WEIGHT_START = 10;
    private static final int SECOND_DIGIT_WEIGHT_START = 11;
    private static final int MODULO_DIVISOR = 11;
    private static final int MINIMUM_VALID_DIGIT = 10;
    private static final int DEFAULT_DIGIT_VALUE = 0;

    public CPF {

        requireNonNull(number, "Document number can't be null");

        if (!isValid(number)) {
            throw new InvalidDocumentException();
        }
    }

    public static CPF of(final String number) {
        return new CPF(number);
    }

    @Override
    public boolean isValid(final String cpf) {

        String number = cpf.replaceAll("[^0-9]", "");

        if (number.length() != CPF_LENGTH) {
            return false;
        }

        if (number.matches("(\\d)\\1{" + CPF_REPEATED_PATTERN_LENGTH + "}")) {
            return false;
        }

        try {
            int sum = 0;
            for (int i = 0; i < FIRST_DIGIT_CALCULATION_LENGTH; i++) {
                sum += Character.getNumericValue(number.charAt(i)) * (FIRST_DIGIT_WEIGHT_START - i);
            }
            int firstDigit = MODULO_DIVISOR - (sum % MODULO_DIVISOR);
            if (firstDigit >= MINIMUM_VALID_DIGIT) {
                firstDigit = DEFAULT_DIGIT_VALUE;
            }

            if (firstDigit != Character.getNumericValue(number.charAt(FIRST_DIGIT_POSITION))) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < SECOND_DIGIT_CALCULATION_LENGTH; i++) {
                sum += Character.getNumericValue(number.charAt(i)) * (SECOND_DIGIT_WEIGHT_START - i);
            }
            int secondDigit = MODULO_DIVISOR - (sum % MODULO_DIVISOR);
            if (secondDigit >= MINIMUM_VALID_DIGIT) {
                secondDigit = DEFAULT_DIGIT_VALUE;
            }

            return secondDigit == Character.getNumericValue(number.charAt(SECOND_DIGIT_POSITION));

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getNumber() {
        return number;
    }
}
