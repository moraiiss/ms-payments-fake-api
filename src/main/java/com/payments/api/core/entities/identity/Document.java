package com.payments.api.core.entities.identity;

public interface Document {

    boolean isValid(String number);

    String getNumber();

}
