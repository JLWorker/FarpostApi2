package org.farpost.farpostapi2.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BadAuthSwaggerRequestException extends RuntimeException{
    public BadAuthSwaggerRequestException() {
        super("Swagger jwt token is invalid or blank");
    }
}
