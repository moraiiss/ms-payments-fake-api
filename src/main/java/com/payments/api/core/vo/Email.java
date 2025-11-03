package com.payments.api.core.vo;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class Email {

    private static final String EMAIL_REGEX =
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String address;

    private Email(final String address) {

        requireNonNull(address, "Email address can't be null");

        if (!isValid(address)) {
            throw new IllegalArgumentException("Email address not is valid");
        }

        this.address = address;
    }

    public static Email of(final String address) {
        return new Email(address);
    }

    public String getAddress() {
        return address;
    }

    private boolean isValid(String address) {

        if (address == null || address.trim().isEmpty()) {
            return false;
        }

        // Remove espaços em branco
        address = address.trim();

        // Verifica o tamanho máximo (RFC 5321)
        if (address.length() > 254) {
            return false;
        }

        // Verifica se contém @ e se tem apenas um @
        if (!address.contains("@") || address.indexOf("@") != address.lastIndexOf("@")) {
            return false;
        }

        // Divide em parte local e domínio
        String[] parts = address.split("@");
        if (parts.length != 2) {
            return false;
        }

        String localPart = parts[0];
        String domain = parts[1];

        // Verifica tamanhos das partes
        if (localPart.isEmpty() || localPart.length() > 64 || domain.isEmpty()) {
            return false;
        }

        // Verifica se não começa ou termina com ponto
        if (localPart.startsWith(".") || localPart.endsWith(".") ||
            domain.startsWith(".") || domain.endsWith(".")) {
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
