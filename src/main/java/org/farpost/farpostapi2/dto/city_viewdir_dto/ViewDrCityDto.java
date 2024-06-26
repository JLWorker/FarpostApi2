package org.farpost.farpostapi2.dto.city_viewdir_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@Getter
public class ViewDrCityDto {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я-_\\s]{3,30}$", message = "Invalid name parameter")
    @Schema(example = "vladivostok")
    private String name;

    @NotNull(message = "Farpost id cannot be null")
    @Schema(example = "12")
    private Integer farpostId;

    public ViewDrCityDto(String name, Integer farpostId) {
        this.name = name;
        this.farpostId = farpostId;
    }
}
