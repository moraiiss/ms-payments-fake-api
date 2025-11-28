package com.payments.api.core.domain.vo;

public interface Document {

    boolean isValid(String number);

    String getNumber();

}
