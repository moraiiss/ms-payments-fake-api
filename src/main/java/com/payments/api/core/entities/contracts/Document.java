package com.payments.api.core.entities.contracts;

public interface Document {
    boolean isValid(String number);
    String getNumber();
}
