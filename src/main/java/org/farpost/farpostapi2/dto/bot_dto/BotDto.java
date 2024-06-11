package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@Getter
public class BotDto {

    @Pattern(regexp = "^[a-zA-Z\\d]{3,50}$")
    @Schema(example = "bot1")
    private String name;

    @NotNull(message = "Vps id cannot be null")
    @Schema(example = "3")
    private Integer vpsId;


}
