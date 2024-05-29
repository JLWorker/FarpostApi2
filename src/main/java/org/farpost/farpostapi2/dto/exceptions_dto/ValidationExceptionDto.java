package org.farpost.farpostapi2.dto.exceptions_dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ValidationExceptionDto {

    @JsonProperty("loc")
    private String location;
    @JsonProperty("msg")
    private String message;
    @JsonProperty
    private String type;

    public ValidationExceptionDto(String location, String message, String type) {
        this.location = location;
        this.message = message;
        this.type = type;
    }
}
