package org.farpost.farpostapi2.exceptions.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(Integer elemId) {
        super(String.format("Element with id %s - not found", elemId));
    }
}
