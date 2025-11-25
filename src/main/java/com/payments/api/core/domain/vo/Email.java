package com.payments.api.core.domain.vo;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public record Email(
    String address
) {

    private static final String EMAIL_REGEX =
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
        + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final int MAX_EMAIL_LENGTH = 254;
    private static final int MAX_LOCAL_PART_LENGTH = 64;
    private static final int EXPECTED_PARTS_AFTER_SPLIT = 2;
    private static final int LOCAL_PART_INDEX = 0;
    private static final int DOMAIN_INDEX = 1;

    public Email {

        requireNonNull(address, "Email address can't be null");

        if (!isValid(address)) {
            throw new IllegalArgumentException("Email address not is valid");
        }

    }

    public static Email of(final String address) {
        return new Email(address);
    }

    private boolean isValid(String address) {

        if (address == null || address.trim().isEmpty()) {
            return false;
        }

        // Remove espaços em branco
        address = address.trim();

        // Verifica o tamanho máximo (RFC 5321)
        if (address.length() > MAX_EMAIL_LENGTH) {
            return false;
        }

        // Verifica se contém @ e se tem apenas um @
        if (!address.contains("@") || address.indexOf("@") != address.lastIndexOf("@")) {
            return false;
        }

        // Divide em parte local e domínio
        String[] parts = address.split("@");
        if (parts.length != EXPECTED_PARTS_AFTER_SPLIT) {
            return false;
        }

        String localPart = parts[LOCAL_PART_INDEX];
        String domain = parts[DOMAIN_INDEX];

        // Verifica tamanhos das partes
        if (localPart.isEmpty() || localPart.length() > MAX_LOCAL_PART_LENGTH || domain.isEmpty()) {
            return false;
        }

        // Verifica se não começa ou termina com ponto
        if (localPart.startsWith(".") || localPart.endsWith(".")
            || domain.startsWith(".") || domain.endsWith(".")) {
            return false;
        }

        // Verifica se não tem pontos consecutivos
        if (address.contains("..")) {
            return false;
        }

        // Verifica se o domínio tem pelo menos um ponto
        if (!domain.contains(".")) {
            return false;
        }

        // Valida com regex
        return EMAIL_PATTERN.matcher(address).matches();
    }
}
