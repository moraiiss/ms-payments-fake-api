package com.payments.api.core.domain.exceptions;

public class ExistingDocumentException extends RuntimeException {
    public ExistingDocumentException() {
        super("This document number provided already existis!");
    }
}
