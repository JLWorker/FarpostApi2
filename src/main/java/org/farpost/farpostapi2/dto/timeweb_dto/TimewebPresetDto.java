package org.farpost.farpostapi2.dto.timeweb_dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class TimewebPresetDto {

    @Schema(example = "1")
    private Integer id;
    @Schema(example = "ru")
    private String location;
    @Schema(example = "1")
    private Integer cpu;
    @Schema(example = "1.0")
    private Float cpuFrequency;
    @Schema(example = "1")
    private Integer ram;
    @Schema(example = "10000")
    private Long disk;
    @Schema(example = "ssd")
    private String discType;

}
