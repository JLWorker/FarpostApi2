package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimewebImagesDto {

    private String id;
    @Schema(example = "Name")
    private String name;
    @Schema(example = "15000")
    private Long size;
    @Schema(example = "9000")
    private Long virtualSize;
    @Schema(example = "24.03.2023")
    private String createdAt;

}
