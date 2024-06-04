package org.farpost.farpostapi2.dto.client_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class ClientVpsDto {

    @NotNull(message = "Preset cannot be null")
    private Integer presetId;

    @NotNull(message = "Os id cannot be null")
    private Integer osId;

    @NotNull(message = "DdosGuard cannot be null")
    private boolean isDdosGuard;

    @NotNull(message = "Availability zone cannot be null")
    @Pattern(regexp = "^[a-z]{3}-\\d$", message = "Invalid availability zone")
    private String availabilityZone;

    @NotNull(message = "Vps name cannot be null")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{3,50}$", message = "Invalid name parameter")
    private String vpsName;

}
