package com.payments.api.core.domain.exceptions;

public class InvalidDocumentException extends RuntimeException {
    public InvalidDocumentException() {
        super("This document number provided is not valid!");
    }
}
