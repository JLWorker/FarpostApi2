package org.farpost.farpostapi2.dto.exceptions_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.farpost.farpostapi2.exceptions.RemoteInvalidRequestException;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class RemoteRequestExceptionDto {

    private String message;

    private String uri;

    @JsonProperty("status")
    private Integer httpStatus;

    @JsonProperty("method")
    private String httpMethod;

    private String classPath;

    public RemoteRequestExceptionDto(RemoteInvalidRequestException remoteInvalidRequestException){
        this.httpStatus = remoteInvalidRequestException.getHttpStatus();
        this.httpMethod = remoteInvalidRequestException.getHttpMethod();
        this.uri = remoteInvalidRequestException.getUri();
        this.message = remoteInvalidRequestException.getMessage();
        this.classPath = remoteInvalidRequestException.getCause().getClass().getName();
    }

}
