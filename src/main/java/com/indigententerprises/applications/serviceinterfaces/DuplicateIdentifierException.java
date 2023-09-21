package com.indigententerprises.applications.serviceinterfaces;

public class DuplicateIdentifierException extends RuntimeException {
    public DuplicateIdentifierException(final String message) {
        super(message);
    }
}
