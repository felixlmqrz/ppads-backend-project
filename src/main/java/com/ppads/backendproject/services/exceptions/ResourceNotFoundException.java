package com.ppads.backendproject.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Resource not found with id " + id);
    }
}
