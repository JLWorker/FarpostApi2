package org.farpost.farpostapi2.dto.client_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @NotNull(message = "Image id cannot be bull")
    private String imageId;

    @NotNull(message = "DdosGuard cannot be null")
    @JsonProperty("is_ddos_guard")
    private Boolean isDdosGuard;

    @NotNull(message = "Availability zone cannot be null")
    @Pattern(regexp = "^[a-z]{3}-\\d$", message = "Invalid availability zone")
    @Schema(example = "spb-4")
    private String availabilityZone;

    @NotNull(message = "Vps name cannot be null")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\d\\s]{3,50}$", message = "Invalid name parameter")
    @Schema(example = "Test1")
    private String vpsName;

}
