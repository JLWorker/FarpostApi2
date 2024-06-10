package org.farpost.farpostapi2.dto.client_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class SuccessClientCreateDto {

    @Schema(example = "The request has been processed, creation vps will take one and a half minutes")
    private String status_message;

    public SuccessClientCreateDto() {
        this.status_message = "The request has been processed, creation vps will take one and a half minutes";
    }
}
