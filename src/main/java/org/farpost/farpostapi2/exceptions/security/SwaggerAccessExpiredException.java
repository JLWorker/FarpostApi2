package org.farpost.farpostapi2.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SwaggerAccessExpiredException extends RuntimeException {

    public SwaggerAccessExpiredException() {
        super("Swagger jwt token expired");
    }
}
