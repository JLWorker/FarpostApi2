package org.farpost.farpostapi2.dto.client_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientDto {

    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{3,50}$", message = "Invalid name parameter")
    @Schema(example = "Ivan")
    private String name;

    @NotEmpty(message = "Boobs cannot be empty")
    @NotNull(message = "Boobs cannot be null")
    @Schema(example = "uhbsjfs49548...")
    private String boobs;

    @Schema(example = "1")
    private Integer vpsId;
}
