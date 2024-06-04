package org.farpost.farpostapi2.exceptions.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidUserCredentials extends RuntimeException{

    public InvalidUserCredentials(String message) {
        super(message);
    }
}
