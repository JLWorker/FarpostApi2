package org.farpost.farpostapi2.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Getter
public class RemoteInvalidRequestException extends IOException {

    private final String uri;
    private final Integer httpStatus;
    private final String httpMethod;

    public RemoteInvalidRequestException(String message, String uri, Integer httpStatus, String httpMethod) {
        super(message);
        this.uri = uri;
        this.httpStatus = httpStatus;
        this.httpMethod = httpMethod;
    }
}
