package org.farpost.farpostapi2.dto.client_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class ClientTgDto {

    @NotNull(message = "Telegram id cannot be null")
    private Integer tgId;

    @Pattern(regexp = "^.*\\B@(?=\\w{5,32}\\b)[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*.{2,50}$",
        message = "Telegram username invalid")
    @NotNull(message = "Telegram username cannot be null")
    @Schema(example = "@Tolik234")
    private String username;

}
