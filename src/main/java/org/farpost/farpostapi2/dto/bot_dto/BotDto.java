package org.farpost.farpostapi2.dto.bot_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@Getter
public class BotDto {

    @Pattern(regexp = "^[a-zA-Z_\\-\\d]{3,50}$")
    private String name;

    @NotNull(message = "Vps id cannot be null")
    private Integer vpsId;


}
