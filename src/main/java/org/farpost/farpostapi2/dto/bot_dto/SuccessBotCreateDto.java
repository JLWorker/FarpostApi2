package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class SuccessBotCreateDto {
    @Schema(example = "The request has been processed, bot initialization will take 2 minutes")
    private String status_message;

    public SuccessBotCreateDto() {
        this.status_message = "The request has been processed, bot initialization will take 2 minutes";
    }
}
