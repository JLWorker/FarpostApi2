package org.farpost.farpostapi2.exceptions.system;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
