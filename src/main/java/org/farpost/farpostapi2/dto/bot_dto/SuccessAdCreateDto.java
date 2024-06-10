package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class SuccessAdCreateDto {

    @Schema(example = "The request has been processed, creation ad will take a couple of seconds")
    private String status_message;

    public SuccessAdCreateDto() {
        this.status_message = "The request has been processed, creation ad will take a couple of seconds";
    }
}
